# kd-tree-api

An API built over Java ML's KDTree implementation.  
Primarily designed as a service for my [weather-api](https://github.com/corinm/weather-api).

## API Documentation
### Create a new tree
**`POST /tree/create`**  
  * Header: `{ Content-Type: application/json }`  
  * Request body data: `[ { key1: 10, key2: 20, data: obj }, ... ]`  
    * The keys can have any name, but must be **numbers** (decimals are okay).  

Example body data:
```
[
  { lat: 36.065805, long: -112.135609, data: { id: 'a1', name: 'Grand Canyon' } },
  { lat: 32.732219, long: -117.149219, data: { id: 'a2', name: 'Balboa Park' } }
  ...
]
```
On success will return an id for the created tree, for querying it
```
{
    id: 12345678910
}
```


### Find closest match to a given key
**`POST /tree/search`**  
  * Header: `{ Content-Type: application/json }`  
  * Request body data: `{ key1: 10, key2: 20 }`  
    * The keys can have any name, but must be **numbers** (decimals are okay).  

Example body data:
```
{ lat: 37.301600, long: -112.945644 }
```
Will return a JSON object containing the `data` attribute of the closest match
```
{
    id: 'a3',
    name: 'Zion National Park'
}
```

## Running Locally

1. Install: Java, Maven and Heroku Toolbelt
2. Run `git clone https://github.com/corinm/kd-tree-api`
3. Run `cd kd-tree-api`
4. Run `mvn install`
5. Run `heroku local` to start the app

## Deploying to Heroku

1. Run `heroku create`
2. Run `git push heroku master`
3. Run `heroku open`

## Attributions
  * This application was built upon Heroku's [java-getting-started](https://github.com/heroku/java-getting-started.git) seed project.
  * The k-dimensional tree implementation used is from the [Java-ML library](http://java-ml.sourceforge.net/).