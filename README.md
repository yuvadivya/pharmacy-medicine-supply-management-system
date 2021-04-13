# pharmacy-medicine-supply-management-system

Project Members
->Divya Achanta
->Aluru Sharanya
->Chadam Roja
->Isukapalli Rakesh Reddy
->Muni kumar Reddy
->Surendra Rao Jajam


Project Overview :

A Pharmaceutical company wants to automate the logic of forming a schedule for their medical representatives to meet the targeted doctors to explain their medicines and its nature for prescription. Based on the response from doctors, the medicine demand will be determined. This should be fed into the system to determine the medicine supply detail to its Pharmacists. The application developed will target this requirement.

Project Details

• Microservices 

1. Authorization:- The Authorization microservice is used to create JWT tokens. It is used for verifying the user credentials, if the user credentials are valid,
it allows to use the other microservices and if the user credentials are not valid, it shown an error message as “Bad Credentials”. The token will be expired
after specific amount of time (30 minutes). The Authorization microservice is passing the token to other microservices for verifying that particular user has
access to open that page or not through feign client.

2. Medicine Stock Microservice: - The intent of this Microservice is to determine the Medicine Stock details in go down area. It provides information on the 
details of the medicines like the medicine name, chemical composition, date of expiry, number of tablets target ailment along with Pharmacy Name. In this microservice, “MedicineStockJpaRepository” is used to fetch the data from h2-database with the help of Spring Boot JPA Queries. “MedicineStockController” is
responsible for processing incoming requests from web page. It invokes business logic of the service, updates the model and returns the view of that methods and 
that should be rendered.

3. Medical Representative Schedule Microservice: - The intent of this microservice is to provide a doctor meet schedule for the medical representatives of the company. And the authorization service is invoked for verifying the token of the user. From the Pre-defined doctor information, “medicalRepresntativeScheduleController” is used for the mapping the available representatives for a period of 5 days, according to the schedule date. The start date of the schedule should be sent as input from the web portal. In that period,if Sunday comes then it should be skipping that particular day to Monday for representatives Schedule meeting.

4. Pharmacy Medicine Supply Microservice: -
The intent of this microservice is to maintain the list of pharmacy medicines and its supply count that the company does business with.It receives the input from 
web portal and pass to this service, once receiving this input it checks the medicine and its number of tablets in stock In the Medicine Stock Service. If the stock count is lesser than demand count. Then it returns an error message. If demand count is lesser than stock count, then it updates the stock count value in Medicine Stock service. And then save the medicine demand and medicine supply list.

5. Pharmacy Portal: -
In the Pharmacy Medicine Supply portal, it allows a member to login. Once the user successfully logged in login page, then it returns into home page. In the home 
page has various operations like we can able to see the medical representatives schedule by providing a start date. And the schedule will be given for 5 calendar 
days, except Sunday. Once the doctor’s response done, the user should be able provide the demand for medicines, then its checks the particular medicine stock is
available in the medicine stock or not. If the medicine was found, then use can give the demand count of the medicine. The medicine demand and supply would be stored in the database.

• Port Number
1. Authorization – 8084
2. Medicine Stock microservice – 8081
3. Pharmacy Supply microservice – 8083
4. Medical Representative Schedule microservice – 8082
5. Pharmacy Supply Portal – 8080

• Database
o All the microservices are independently deployed.
o An In-memory database (H2 database) has been used in the application.
H2 Console links
I. http://localhost:8080/h2-console
Tables
1) REP_SHECDULE
2) MEDICINE_SUPPLY
3) MEDICINE_DEMAND
II. http://localhost:8084/api/auth/h2-console
Tables
1) USER
III. http://localhost:8081/api/medicine-stock/h2-console
Tables
1) MEDICINE_STOCK
IV. http://localhost:8082/api/medical-representative-schedule-service/h2-console
Tables
1) MEDICAL_REPRESENTATIVES
V. http://localhost:8083/api/pharmacy-medicine-supply/h2-console
Tables
1) MEDICAL_DEMAND
2) MEDICAL_SUPPLY

• AWS
Every service is deployed in the AWS CodeCommit using git commands.
