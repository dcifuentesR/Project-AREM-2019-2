<!-- ABOUT -->  
## About The Project  
  
This is a web server that is able to deliver HTML pages and PNG images. It also provides an IoC framework for the construction of websites using POJOS.
This project also contains a sample web page that can multiply two numbers.
  
### Built With  
  
* [Java](https://www.java.com/es/download/)  
* [Maven](https://maven.apache.org/)  
  
<!-- GETTING STARTED -->  
## Getting Started  
  
To get a local copy up and running follow these simple steps.  
  
### Prerequisites  
  
The following instructions will have you set and ready to go in any Linux distro:  
* Maven  
```shell script  
sudo apt install maven  
```  
* Java  
```shell script  
sudo apt install openjdk-8-jdk  
```  
  
### Installation  
   
1. Clone the repository  
```shell script  
git clone https://github.com/develalopez/basic-web-service.git  
```  
2. If you want to use the project as a dependency of another project, install it in your local repository:  
```shell script  
mvn clean install  
```  
3. To run the service locally, you execute the following from the root folder:  
```shell script  
java -cp target/classes:target/dependency/* edu.eci.arem.server.Controller
```  

This project is also available [in this Heroku website](https://dcifuentes-arem-p1.herokuapp.com/).

<!-- LICENSE -->  
## License  
  
Distributed under the GNU General Public License. See `LICENSE` for more information.  
  
<!-- CONTACT -->  
## Contact  
  
Daniel Mauricio Cifuentes Ramirez - daniel.cifuentes-r@mail.escuelaing.edu.co  
  
Project Link: [https://github.com/dcifuentesR/Project-AREM-2019-2](https://github.com/dcifuentesR/Project-AREM-2019-2)
