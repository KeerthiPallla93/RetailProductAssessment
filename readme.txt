MyRetail RESTful service

This service consists of 2 RestCalls
REQ: Need to connect to your DynamoDB with credentials in order to save and get productPrice Details

1)GetProductDetails based on id given

Based on the given Id, will interact with REDSKY API and get Product ID and Name.
if(ProductId details are not Found) throw NotFoundException else
Once get successful response, will get PriceInfo details from NOSQL.
If PriceInfo Not Found in NOSQL, return null for PriceInfo

2)PostProductDetails into NOSQL
Update ProductDetails to DynamoDB
