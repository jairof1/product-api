package com.dislicores.api.b2c.app.product.provider;

import com.dislicores.api.b2c.app.product.domain.firebase.FirebaseCartDto;
import com.dislicores.api.b2c.app.product.exception.ProductException;
import com.dislicores.api.b2c.app.product.util.MessagesUtil;
import com.dislicores.api.b2c.app.product.util.ProductConstants;
import com.dislicores.api.b2c.app.product.util.UtilConverter;
import com.dislicores.api.b2c.app.product.util.UtilProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
@Slf4j
public class FCMProvider {

    private final Firestore firestore;
    private final UtilProperties utilProperties;
    private final MessagesUtil messagesUtil;

    public List<FirebaseCartDto> getAbandonnedCart(int tried, int timeMinutes) {
        try {
            // Obtener la fecha y hora actual
            Date fechaActual = new Date();

            // Crear un objeto Calendar
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaActual);

            // Restar x tiempo en minutos a la fecha actual
            calendar.add(Calendar.MINUTE, -timeMinutes);

            // Obtener el tiempo limite
            Date timeLimit = calendar.getTime();

            ApiFuture<QuerySnapshot> query = firestore.collection(utilProperties.getCollectionFirebase())
                    .whereLessThanOrEqualTo("date", timeLimit)
                    .get();

            List<QueryDocumentSnapshot> documents = query.get().getDocuments();

            List<FirebaseCartDto> cartList = documents.stream()
                    .map(document -> {
                        FirebaseCartDto cartDto = document.toObject(FirebaseCartDto.class);
                        cartDto.setId(document.getId());
                        return cartDto;
                    })
                    .peek(document -> document.setProductsList(UtilConverter.convertJsonToClass(document.getProducts(), new TypeReference<>() {})))
                    .filter(cart -> !cart.getProductsList().isEmpty() && cart.getNotification() == tried - 1)
                    .toList();

            return cartList;
        } catch (Exception e) {
            log.info("error getAbandonnedCart");
            throw new ProductException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ProductConstants.DPA000, messagesUtil.getMessage(ProductConstants.DPA000));
        }
    }

    public void updateCart(FirebaseCartDto cartDto){
        try {
            var collectionReference = firestore.collection(utilProperties.getCollectionFirebase());

            var documentReferenceApiFuture = collectionReference.document(cartDto.getId());

            Map<String, Object> data = documentReferenceApiFuture.get().get().getData();
            if (data.containsKey("notification")){
                data.replace("notification", cartDto.getNotification() + 1);
            } else {
                data.put("notification", cartDto.getNotification() + 1);
            }

            documentReferenceApiFuture.update(data);
            var document = documentReferenceApiFuture.get().get();
            log.info("Documento actualizado con Id: {}" , document.getId());
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error actualizando carrito notificacion push...", e);
        }
    }
}