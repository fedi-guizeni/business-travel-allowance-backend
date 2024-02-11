# introduction 
The business travel allowance (A.V.A) is granted to cover the cost of the beneficiary's stay abroad, as part of his or her professional activities.
It is therefore a transferable envelope in dinars (composed of the amount of the transfer entitlement in dinars fixed according to the type of AVA). 
There are three types of business travel allowance: AVA Exporter AVA Other activities AVA Foreign market

## Getting started
in this project we adopted a monolithic architecture using spring boot , spring , spring secuity , jwt , jpa , hibernate , maven and postresql for the database

<h3 align="left">Languages and Tools:</h3>
<p align="left"> <a href="https://angular.io" target="_blank" rel="noreferrer"> <img src="https://angular.io/assets/images/logos/angular/angular.svg" alt="angular" width="40" height="40"/> </a> <a href="https://getbootstrap.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/bootstrap/bootstrap-plain-wordmark.svg" alt="bootstrap" width="40" height="40"/> </a> <a href="https://www.w3schools.com/css/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/css3/css3-original-wordmark.svg" alt="css3" width="40" height="40"/> </a> <a href="https://git-scm.com/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/git-scm/git-scm-icon.svg" alt="git" width="40" height="40"/> </a> <a href="https://www.w3.org/html/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/html5/html5-original-wordmark.svg" alt="html5" width="40" height="40"/> </a> <a href="https://www.java.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="40" height="40"/> </a> <a href="https://www.postgresql.org" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/postgresql/postgresql-original-wordmark.svg" alt="postgresql" width="40" height="40"/> </a> <a href="https://postman.com" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/getpostman/getpostman-icon.svg" alt="postman" width="40" height="40"/> </a> <a href="https://spring.io/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="40" height="40"/> </a> <a href="https://www.typescriptlang.org/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/typescript/typescript-original.svg" alt="typescript" width="40" height="40"/> </a> </p>

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
Here you'll find a video demonstration of the application.[Video](https://drive.google.com/file/d/1pOFuTJTH5MNRxAnoOjRvTn7aOX5qFDlE/view?usp=sharing)


## The End
In the end, I hope you enjoyed the application and find it useful, as I did when I was developing it. If you would like to enhance, please:
- Open PRs,
- Give feedback,
- Add new suggestions, and
- Finally, give it a 🌟.




