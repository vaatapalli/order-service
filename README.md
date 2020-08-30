# Order-service

Development server Navigate to http://localhost:8080/.

Technology Stack :
-----------------

Java 8

Apache Tomcat/9.0.37

H2 db (H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:order_service')

user name : sa

password : ""

Spring Boot 2.3.3.RELEASE

@GetOrderList
http://localhost:8080/orders

Note :
------
if Item-service is down it will return "fallbackMethod" and the resultant response will be

--------------------------------
[
    {
        "customerName": "cache customer",
        "orderDate": "2020-08-30",
        "shipping_address": "cache Address",
        "order_items": [
            {
                "product_code": 1,
                "product_name": "cache product 1",
                "quantity": 10
            },
            {
                "product_code": 2,
                "product_name": "cache product 2",
                "quantity": 20
            }
        ],
        "total_cost": 123
    }
]

------------------------------------------------
 

@GetSelectedOrder
http://localhost:8080/orders/{customerName}}

@PostOrder 
http://localhost:8080/orders

Sample body :
------------
{
        "customerName": "epam",
        "shipping_address": "Hyderabad",
        "order_items": [
							{
								"product_code": 5,
								"product_name": "product 5",
								"quantity": 1
							},
							{
								"product_code": 6,
								"product_name": "product 6",
								"quantity": 1
							}
						],
		"total_cost" : "250"
	
}




