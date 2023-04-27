# Asapp-Lead-SDET-Challenge

<p align="center">
  <img src="images/logo.PNG" width="400" height="250"/>
</p>

## Description

Asapp-Lead-SDET-Challenge is an e2e framework. It was developed using Java, gradle, and Selenium.

## Quickstart

Assuming Google Chrome and Git installed:

```bash
# For Mac
# install java 11 (skip if already installed)
brew tap AdoptOpenJDK/openjdk
brew cask install adoptopenjdk11

# install chromedriver (skip if already installed)
brew cask install chromedriver

# clone the project and launch the tests
git clone https://github.com/alandiaz08/assap-SDET-Challenge
cd Asapp-Lead-SDET-Challenge

# For Windows
# install java 11 
choco install adoptopenjdk11

# install chromedriver (skip if already installed)
choco install chromedriver

# clone the project and launch the tests
git clone https://github.com/alandiaz08/assap-SDET-Challenge
cd Asapp-Lead-SDET-Challenge 
```

This will execute all the tests using your local Chrome browser.


## Developer Installation

To install the dependencies we use *brew* for mac. You can install it from here: <https://brew.sh/>

To install the dependencies we use *chocolatey* for windows. You can install it from here: <https://chocolatey.org/>
### 1. Install git

```bash
# For Mac
brew install git

# For Windows
choco install git
```

### 2. Install Java 11 SDK

```bash
# For Mac
brew tap AdoptOpenJDK/openjdk
brew cask install adoptopenjdk11

# For Windows
choco install AdoptOpenJDK/openjdk
choco install adoptopenjdk11
```

Test that java 11 is correctly installed with

```bash
java -version
```

If it was correctly installed you should see something like

 ```bash
openjdk version "11.0.6" 2020-01-14
OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.6+10)
OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.6+10, mixed mode)
```

### 3. Install IntelliJ IDEA CE

IntelliJ IDEA CE can be installed from the jetbrains website: <https://www.jetbrains.com/idea/download/#section=mac>  
It can also be installed using brew:

```bash
# For Mac
brew cask install intellij-idea-ce

# For Windows
choco install intellij-idea-ce
```

### 4. Install chromedriver

```bash
# For Mac
brew cask install chromedriver

# For Windows
choco install chromedriver
```

### 5. Install docker

```bash
# For Mac
brew install --cask docker

# For Windows
choco install docker-desktop
```

## How to run

### 1. Run Docker

Precondition: you need to have downloaded the "ASAPP - QA Automation Challenge" docker image.

```bash
# Build the Images for API and UI:

docker build ./src/api -t asapp-qa-challenge-api

docker build ./src/ui -t asapp-qa-challenge-ui

# Start them through docker-compose:

docker-compose up -d

Browse to localhost:3000 to access the challenge UI.

Browse to localhost:5000/api/docs/ for the API spec.

Command above will run the containers in background, but you can always follow logs with docker-compose logs -f.

To stop containers you can run docker-compose stop.
```

### 2. Run tests

```bash
## Scenarios
Test 1: 
Script name: registerNewUser
1. Open the Asapp challenge login page.
2. Register a new user with a random generated username and password.
3. Assert that the title of the register match with "Thank you!".
4. Assert that the sub title of the register match with "Please insert Username and Password".
5. Log in with the previously registered user.
6. Assert that the product list of the store is not empty.

Test 2: 
Script name: addProductsToCart
1. Open the Asapp challenge login page.
2. Register a new user with a random generated username and password.
3. Log in with the previously registered user.
4. Select pen product from the store and select “3” pens of quantity to add to the cart.
5. Add the product to the cart.
6. Navigate to the cart.
7. Assert that the product in the cart is "ASAPP Pens".
8. Assert that the quantity of products selected in the cart is "3".

Test 3:
Script name: deleteAProductToCart
1. Open the Asapp challenge login page.
2. Register a new user with a random generated username and password.
3. Log in with the previously registered user.
4. Select Stickets from the store.
5. Add "2" of quantity.
6. Add the product to the cart.
7. Navigate to the cart.
8. Remove the product from the cart.
9. Assert that the message "OH NO YOUR CART IS EMPTY" is displayed on the cart page.

Test 4: buyAProductToCart
Script name: buyAProductToCart
1. Open the Asapp challenge login page.
2. Register a new user with a random generated username and password.
3. Log in with the previously registered user.
4. Select waterBottle from the store.
5. Add "2" of quantity Agrega el producto al carrito.
6. Navigate to the cart.
7. Click on Buy.
8. Go to Thank you page.
9. Assert that the title of the thank you component is "Thank you!".
10. Assert that the description of the thank you component is 
"We will be sending you a link by e-mail to complete payment. We only accept DLacy Coins!!"
11. Assert the picture of the thank you component is displayed.


## Run All tests
./gradlew test --tests "*.TestDemo"

## Run one test
./gradlew test --tests "*.Name_Of_The_Test"

```