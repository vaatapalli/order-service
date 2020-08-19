# Order-service

Development server Navigate to http://localhost:8080/.

Technology Stack :

Java 8
Apache Tomcat/9.0.37
H2 db (H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:order_service')
user name : sa
password : ""
Spring Boot 2.3.3.RELEASE

GetOrder
http://localhost:8080/get

@GetSelectedOrder
http://localhost:8080/get/{customerName}

@PostOrder 
http://localhost:8080/createorder

Sample body :
{
        "customerName": "customer name",
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


