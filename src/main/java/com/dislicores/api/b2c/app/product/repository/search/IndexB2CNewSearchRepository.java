package com.dislicores.api.b2c.app.product.repository.search;

import com.dislicores.api.b2c.app.product.domain.carrousel.OriginSection;
import com.dislicores.api.b2c.app.product.model.search.IndexB2CNewSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface IndexB2CNewSearchRepository extends ElasticsearchRepository<IndexB2CNewSearch, String> {

    @Query("""
            {
                "bool": {
                  "must": [
                    {
                      "term": {
                        "contentType.keyword": {
                          "value": "categoria"
                        }
                      }
                    },
                    {
                      "term": {
                        "fields.plataforma.es-CO.keyword": {
                          "value": "B2C"
                        }
                      }
                    },
                    {
                      "term": {
                        "fields.nombre.es-CO.keyword": {
                          "value": "Vinos"
                        }
                      }
                    }
                  ]
                }
              }
            """)
    Page<IndexB2CNewSearch> findCategoryWine(Pageable pageable);


    @Query("""
            {
                "bool": {
                  "must": [
                    {
                      "term": {
                        "contentType.keyword": {
                          "value": "storesAppB2c"
                        }
                      }
                    },
                    {
                      "term": {
                        "entryId.keyword": {
                          "value": "?0"
                        }
                      }
                    }
                  ]
                }
              }
            """)
    Page<IndexB2CNewSearch> findWhitelabelByEntryId(String whitelabel, Pageable pageable);

    @Query("""
            {
                "bool": {
                  "must": [
                    {
                      "term": {
                        "contentType.keyword": {
                          "value": "nivelDelClub"
                        }
                      }
                    },{
                      "term": {
                        "fields.priceTables.es-CO.keyword": {
                          "value": "?0"
                        }
                      }
                    }
                  ]
                }
              }
            """)
    Page<IndexB2CNewSearch> findLevelClubByEntryId(String levelClub, Pageable pageable);

    @Query("""
            {
                "bool": {
                  "must": [
                    {
                      "term": {
                        "fields.plataforma.es-CO.keyword": {
                          "value": "B2C"
                        }
                      }
                    },{
                      "term": {
                        "contentType.keyword": {
                          "value": "producto"
                        }
                      }
                    },{
                      "terms": {
                        "entryId.keyword": ?0
                      }
                    }
                  ]
                }
              }
            """)
    Page<IndexB2CNewSearch> findProductsByEntryId(List<String> entryIds, Pageable pageable);

    @Query("""
            {
                 "bool": {
                   "must": [
                     {
                         "match": {
                             "contentType": "producto"
                         }
                     },
                     {
                         "match": {
                             "fields.plataforma.es-CO": "B2C"
                         }
                     },
                     {
                         "match": {
                             "fields.sku.es-CO": "?0"
                         }
                     }
                   ]
                 }
            }
            """)
    Page<IndexB2CNewSearch> findProductBySku(String sku, Pageable pageable);

    @Query("""
            {
                "bool": {
                  "must": [
                    {
                      "term": {
                        "contentType.keyword": {
                          "value": "banner_id"
                        }
                      }
                    }, {
                      "term": {
                        "fields.mostrarEnAppB2c.es-CO": {
                          "value": true
                        }
                      }
                    }, {
                      "term": {
                        "entryId.keyword": {
                          "value": "?0"
                        }
                      }
                    }
                  ]
                }
              }
            """)
    Page<IndexB2CNewSearch> findBannersByEntryId(String bannerId, Pageable pageable);

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
                    }, {
                      "term": {
                        "fields.plataforma.es-CO.keyword": {
                          "value": "B2C"
                        }
                      }
                    }, {
                      "term": {
                        "fields.clubDislicores.es-CO.keyword": {
                          "value": "Club Dislicores"
                        }
                      }
                    }
                  ]
                }
              }
            """)
    Page<IndexB2CNewSearch> findExclusiveClubProducts(Pageable pageable);

    @Query("""
            {
              "bool": {
                "must": [
                  {
                    "term": {
                      "contentType.keyword": {
                        "value": "carrouselHome"
                      }
                    }
                  },
                  {
                    "term": {
                      "fields.seccionOrigen.es-CO.keyword": {
                        "value": "?0"
                      }
                    }
                  }
                ]
              }
            }
            """)
    Page<IndexB2CNewSearch> findCarrouselHome(OriginSection originSection, Pageable pageable);

    @Query("""
            {
                "bool": {
                    "must": [
                        {
                            "term": {
                                "contentType.keyword": {
                                    "value": "categoria"
                                }
                            }
                        },
                        {
                            "terms": {
                                "entryId.keyword": ?0
                            }
                        }
                    ]
                }
            }""")
    Page<IndexB2CNewSearch> findSkuPrioritized(List<String> whitelabel, Pageable pageable);

    @Query(value = """
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
                      "entryId.keyword": {
                        "value": "?0"
                      }
                    }
                  }
                ]
              }
            }
            """)
    Optional<IndexB2CNewSearch> findProductsById(String entryId);
}
