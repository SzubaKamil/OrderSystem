# OrderSystem
- <a href="https://github.com/SzubaKamil/OrderSystem/blob/main/database/database_structure.png">Database design</a>
- <a href="https://github.com/SzubaKamil/OrderSystem/blob/main/screen.png">Frontend screen</a>
# 
# Basic Overview:

Fully functional system for managing order dedicated for client who cooperative with printing houses. 
System allows to order all printed products like books, brochure, leaflet, envelopes, picture etc. according to the specification entered by the user. 
System improves order preparation process from create new product by create order to print order in pdf and attach to new outlook email. Completes email: subject, content,  recipients and cc by template.
In the same scheme, the system creates a pdf and an email with a price inquiry

# Features:
 - Login panel 
 - User Role: 
	- ADMIN: User List, Add, edit, delete User
	- USER: All functionality
 - Supports many companies (client work for few cooperative together company)
 - Create, edit, delete: Company, Contractor, Product Details, Product, Payment Term, Order, Campaign
 - History: Product, Contractor
 - Search: Product, Order, Campaign
 - Filter order: sent, unsent, all
 - Print to pdf: 
	- Product order 
	- Product price inqury
 - Create outlook order / inqury email: 
	- Recipient from database,
	- CC recipient from user setting 
	- attache pdf
        - Subject file name 
	- Personalization Gener (Sir, Madame) 
	- Template messega content
	- Personalized mail settings for each user
- Campaign 
	- group product 
	- simplifies process creating orders through adding common fields (delivery date, delivery address, payment term, quantity) for many product. 
	- creating order by by one button  

# Tech Stack:
	- Java 11
	- Spring-boot 2.6.0
	- Hibernate 5.6.5.Final
	- mysql-connector-java 8.0.27
	- c3p0 0.9.5.5
 	- jaxb-api 2.31
	- itextpdf 5.5.13.3
  
