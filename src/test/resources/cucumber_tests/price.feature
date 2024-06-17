Feature: price retrieve

  Scenario Outline: should retrieve prices by product, brand and application datetime
    When a call is made to /api/v1/price?applicationDateTime=<applicationDateTime>&productId=<productId>&brandId=<brandId> endpoint with GET method
    Then the response status code is 200
    And response contains data from file <expectedFile>

    Examples:
      | applicationDateTime | productId | brandId | expectedFile |
      | 2020-06-14T10:00:00 | 35455 | 1 | expected-price1.json |
      | 2020-06-14T16:00:00 | 35455 | 1 | expected-price2.json |
      | 2020-06-14T21:00:00 | 35455 | 1 | expected-price1.json |
      | 2020-06-15T10:00:00 | 35455 | 1 | expected-price3.json |
      | 2020-06-16T21:00:00 | 35455 | 1 | expected-price4.json |