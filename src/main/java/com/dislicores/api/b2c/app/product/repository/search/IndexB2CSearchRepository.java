package com.dislicores.api.b2c.app.product.repository.search;

import com.dislicores.api.b2c.app.product.model.search.IndexB2CSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface IndexB2CSearchRepository extends ElasticsearchRepository<IndexB2CSearch, String> {

    @Query("""
            {
                "bool": {
                  "must": [
                    {
                      "term": {
                        "contentType.keyword": {
                          "value": "producto"
                        }
                      }
                    },
                    {
                      "term": {
                        "fields.sku.es-CO.keyword": {
                          "value": "?0"
                        }
                      }
                    }
                  ]
                }
              }
            """)
    Page<IndexB2CSearch> findProductBySku(String skuId, Pageable pageable);

    @Query("""
            {
                "bool": {
                  "filter": [
                    {
                      "term": {
                        "entryId.keyword": "doc-categories-001"
                      }
                    }
                  ]
                }
              }
            """)
    Page<IndexB2CSearch> findCategories(PageRequest of);

    @Query("""
            {
              "bool": {
                "must": [
                  {
                    "terms": {
                      "entryId.keyword": ?0
                    }
                  }
                ]
              }
            }
            """)
    Page<IndexB2CSearch> findProductsByEntryId(List<String> entryIdList, Pageable pageable);

    @Query("""
            {
              "bool": {
                "must": [
                  {
                    "term": {
                      "contentType.keyword": {
                        "value": "producto"
                      }
                    }
                  },
                  {
                    "terms": {
                      "fields.sku.es-CO": ?0
                    }
                  },
                  {
                    "nested": {
                      "path": "fields.listaPrecioInventarioObjectDtos5",
                      "query": {
                        "bool": {
                          "must": [
                            {
                              "match": {
                                "fields.listaPrecioInventarioObjectDtos5.listPrice": "?1"
                              }
                            },
                            {
                              "match": {
                                "fields.listaPrecioInventarioObjectDtos5.bodega": "?2"
                              }
                            }
                          ]
                        }
                      }
                    }
                  }
                ]
              }
            }
            """)
    Page<IndexB2CSearch> findProductSkusByListPriceAndBodega(List<String> skus, String listPrice, String bodega, Pageable of);
}
