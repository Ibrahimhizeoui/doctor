# doctor
This service provides informations about user three health related questions using spring boot.

## Instalation
To install and test this service you need just to clone it and have java and maven installed in you system.

`mvn test`


## Endpoints
Request type | Target path | Response
------------ | ------------- |--------------
post | /consultation | OK (201) The Consultation has successfully been saved
get | {user_id}/consultation |OK (200) The user and consultations was successfully been fetched else (404) user not found


## Usage

### Create a consultation
To Create a consultation, We can provide a simple form with 3 and submit the data to the api. The response to an HTTP POST request might look like:
```  
    {
		id: 1,
		firstanswer: "lorem ipsum",
		secondanswer: "lorem ipsum",
        thirdanswer: "lorem ipsum",
        user: {
        	id: 1
            username: 
        }
	}
```

### Retrive a collection of consultation
We can get all user consultation by consume the get request `{user_id}/consultation`
The response to an HTTP GET request might look like:
```  
    [{
		id: 1,
		firstanswer: "lorem ipsum",
		secondanswer: "lorem ipsum",
        thirdanswer: "lorem ipsum"
	},
    {
		id: 2,
		firstanswer: "lorem ipsum",
		secondanswer: "lorem ipsum",
        thirdanswer: "lorem ipsum"
	}
    {
		id: 2,
		firstanswer: "lorem ipsum",
		secondanswer: "lorem ipsum",
        thirdanswer: "lorem ipsum"
	}
    ...
    ]
```


## Structure

### Model layer

See the model folder, there are two objects which are User and Consultation. I thought to do One to Many relation between them.

### Dao layer
I used two Spring Data JPA repositories to handle the database interactions and embaded database to store datastructure.	

### Testing the Service
Look at `/src/test/java/com/ib92/doctor/api`
The first thing to look at is this @Before-annotated setup method. The first thing the setup method does is instantiate a MockMvc  the MockMvc is the center piece: all tests will invariably go through the MockMvc type to mock HTTP requests against the service. 

```
mockMvc.perform(get("/" + userId + "/consultations/"
                + this.bookmarkList.get(0).getId()))
                .andExpect(status().isOk())
```
