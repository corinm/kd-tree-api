# kd-tree-api

An API built over Java ML's KDTree implementation. Primarily designed as a service for my [weather-api](https://github.com/corinm/weather-api).

## API Documentation
### Create a new tree
**`POST /tree/create`**  
  * Header: `{ Content-Type: application/json }`  
  * Request body data: `[ { key1: 10, key2: 20, data: obj }, ... ]`  
    * The keys must be called key1 and key2.  
    * The values must be **numbers** (decimals and negatives are okay).  
    * Data can be any JavaScript object.  

Example body data:
```
[
  { key1: 36.065805, key2: -112.135609, data: { id: 'a1', name: 'Grand Canyon' } },
  { key1: 32.732219, key2: -117.149219, data: { id: 'a2', name: 'Balboa Park' } }
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
    * The keys must be called key1 and key2.  
    * The values must be **numbers** (decimals and negatives are okay).  
    * Data can be any JavaScript object.  

Example body data:
```
{
  treeId: 12345678910,
  key: {
    key1: 37.301600,
    key2: -112.945644
  }
}
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
