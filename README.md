# introduction 
The business travel allowance (A.V.A) is granted to cover the cost of the beneficiary's stay abroad, as part of his or her professional activities.
It is therefore a transferable envelope in dinars (composed of the amount of the transfer entitlement in dinars fixed according to the type of AVA). 
There are three types of business travel allowance: AVA Exporter AVA Other activities AVA Foreign market

## Getting started
in this project we adopted a monolithic architecture using spring boot , spring , spring secuity , jwt , jpa , hibernate , maven and postresql for the database

## UML class diagram 
![image](https://github.com/fedi-guizeni/business-travel-allowance-backend/assets/78599201/361ba841-9746-4cdd-b8d2-ea20098aaccc)

##  The lifecycle of an A.V.A. file
This chart shows the life cycle of an A.V.A. file, i.e. the stages a file goes through before it becomes active: 
Each A.V.A. file goes through four stages, the first two at branch level and the last two at head office level.
 Each file added by a branch-maker has a validation status of zero and a file status of zero. 
Each file approved by a branch checker has a validation status of "awaiting regulatory approval" and a file status of zero.
Secondly, we will call the "Regulatory- maker" and "Regulatory- checker" verification agents at head office.
Each file validated by a "Regulatory- maker" has a validation status of "Awaiting final regulatory approval" and a file status of zero. If the file has been rejected, the validation status is equal to "Rejected by regulatory authority" and the file status is zero. 
Each file validated by a regulatory checker has a validation status of ''Valid'' and a file status of ''Active'', i.e. ready for use. If the file has been rejected, the validation status is equal to ''Rejected by regulatory checker'' and the file status is null. 
Files rejected by either rg m or rg c will be retrieved by Branch Maker and the same steps will be repeated.

![image](https://github.com/fedi-guizeni/business-travel-allowance-backend/assets/78599201/8ecca298-3c21-4d66-adad-34ecf89a4deb)

## System packages Structure
```java
JwtSecurity
Listener
Service
constant
controller
entities
enumeration
exception
filter
repository
security
AvaApplication.java
```

## Cloning It
Open git bash command line, and then simply you can clone the project under any of your favorite places as the following:
```bash
git clone https://github.com/fedi-guizeni/business-travel-allowance-backend.git
```
## Front end 
In this link, you can find the front-end (Angular) application repository  [business-travel-allowance-frontend](https://github.com/fedi-guizeni/business-travel-allowance-frontend)

## Application demonstration
Here you'll find a video demonstration of the application . 
