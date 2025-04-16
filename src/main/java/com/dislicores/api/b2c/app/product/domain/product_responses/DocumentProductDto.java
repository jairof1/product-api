package com.dislicores.api.b2c.app.product.domain.product_responses;

import com.dislicores.api.b2c.app.product.domain.product_responses.document_bases.*;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentProductDto {

    private String entryId;
    private String environment;
    private String contentType;
    private FieldsProductDetail fields;

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FieldsProductDetail{
        private BaseEsCoListSys imagenes;
        private ProductBrand brandDto;
        private BaseEsCoDouble nivelDeAlcohol;
        private BaseEsCoSys paisRef;
        private List<ProductCategory> categoryPath;
        private BaseEsCoListString premiosB2c;
        private BaseEsCoString b2cTipoDeBotella;
        private BaseEsCoString nombre;
        private BaseEsCoBoolean digital;
        private BaseEsCoListString acompanante;
        private BaseEsCoDouble maximoAVender;
        private JsonNode specifications;
        private BaseEsCoListString b2cCepa;
        private BaseEsCoString b2cIdProducto;
        private BaseEsCoListString etiquetasPersonalizadasB2c;

        @JsonProperty(value = "B2cDescripcion")
        private BaseEsCoString b2cDescripcion;//TODO: eliminar en futuro paso a produccíon
        @JsonProperty("descripcionB2C")
        private BaseEsCoJsonNode descriptionB2C;
        private BaseEsCoSys marcaB2C;
        private ProductCountry countryDto;
        private BaseEsCoSys subcategoria;
        private List<ProductListaPrecios> listaPrecioInventarioObjectDtos5;
        private BaseEsCoString sku;
        private ProductImageDto imageProductDto;
        private List<ProductImageDto> imagesList;
        private BaseEsCoString slug;

        @Data
        @Builder(toBuilder = true)
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class ProductBrand{
            private String entryId;
            private String enviroment;
            private String contentType;
            private BrandFields fields;

            @Data
            @Builder(toBuilder = true)
            @NoArgsConstructor
            @AllArgsConstructor
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class BrandFields{
                private BaseEsCoString nombreMarca;
            }
        }

        @Data
        @Builder(toBuilder = true)
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class ProductImageDto{
            private String entryId;
            private String enviroment;
            private String contentType;
            private ImageFields fields;

            @Data
            @Builder(toBuilder = true)
            @NoArgsConstructor
            @AllArgsConstructor
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class ImageFields{
                private ImageFile file;
                private BaseEsCoString title;

                @Data
                @Builder(toBuilder = true)
                @NoArgsConstructor
                @AllArgsConstructor
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class ImageFile{
                    @JsonAlias("es-CO")
                    private ImageFileEsCo esCO;

                    @Data
                    @Builder(toBuilder = true)
                    @NoArgsConstructor
                    @AllArgsConstructor
                    @JsonIgnoreProperties(ignoreUnknown = true)
                    public static class ImageFileEsCo{
                        private String fileName;
                        private ImageDetails details;
                        private String contentType;
                        private String url;

                        @Data
                        @Builder(toBuilder = true)
                        @NoArgsConstructor
                        @AllArgsConstructor
                        @JsonIgnoreProperties(ignoreUnknown = true)
                        public static class ImageDetails{
                            private FileDetails image;
                            private Double size;

                            @Data
                            @Builder(toBuilder = true)
                            @NoArgsConstructor
                            @AllArgsConstructor
                            @JsonIgnoreProperties(ignoreUnknown = true)
                            public static class FileDetails{
                                private Double width;
                                private Double height;
                            }
                        }
                    }
                }

            }
        }

        @Data
        @Builder(toBuilder = true)
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class ProductCountry{
            private String entryId;
            private String enviroment;
            private String contentType;
            private CountryFields fields;

            @Data
            @Builder(toBuilder = true)
            @NoArgsConstructor
            @AllArgsConstructor
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class CountryFields{
                private BaseEsCoString codigoPaisErp;
                private BaseEsCoSys bandera;
                private BaseEsCoString codigoDePais;
                private BaseEsCoString nombrePais;
                private CountryFlag flagDto;

                @Data
                @Builder(toBuilder = true)
                @NoArgsConstructor
                @AllArgsConstructor
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class CountryFlag {
                    private String entryId;
                    private String enviroment;
                    private String contentType;
                    private FlagFields fields;

                    @Data
                    @Builder(toBuilder = true)
                    @NoArgsConstructor
                    @AllArgsConstructor
                    @JsonIgnoreProperties(ignoreUnknown = true)
                    public static class FlagFields {
                        private ImageFile file;
                        private BaseEsCoString description;
                        private BaseEsCoString title;

                        @Data
                        @Builder(toBuilder = true)
                        @NoArgsConstructor
                        @AllArgsConstructor
                        @JsonIgnoreProperties(ignoreUnknown = true)
                        public static class ImageFile{
                            @JsonAlias("es-CO")
                            private ImageFileEsCo esCO;

                            @Data
                            @Builder(toBuilder = true)
                            @NoArgsConstructor
                            @AllArgsConstructor
                            @JsonIgnoreProperties(ignoreUnknown = true)
                            public static class ImageFileEsCo{
                                private String fileName;
                                private ImageDetails details;
                                private String contentType;
                                private String url;

                                @Data
                                @Builder(toBuilder = true)
                                @NoArgsConstructor
                                @AllArgsConstructor
                                @JsonIgnoreProperties(ignoreUnknown = true)
                                public static class ImageDetails{
                                    private FileDetails image;
                                    private Double size;

                                    @Data
                                    @Builder(toBuilder = true)
                                    @NoArgsConstructor
                                    @AllArgsConstructor
                                    @JsonIgnoreProperties(ignoreUnknown = true)
                                    public static class FileDetails{
                                        private Double width;
                                        private Double height;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
