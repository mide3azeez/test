function() {   
  var env = karate.env; // get system property 'karate.env'
  if (!env) {
    env = 'QA';
  }


  var config = {
    baseURL : 'https://api-football-v1.p.rapidapi.com/v3',
    xRapidApiHost : 'api-football-v1.p.rapidapi.com',
    xRapidApiKey : 'ee13a762e9msh6cd9b3f5db3d754p12f0bfjsn52bc5687f3bf'
 }


    /* This sets config for various environment */
  if (env == 'QA') {
    config.baseURL = 'https://api-football-v1.p.rapidapi.com/v3';
    config.xRapidApiHost = 'api-football-v1.p.rapidapi.com',
    config.xRapidApiKey = 'ee13a762e9msh6cd9b3f5db3d754p12f0bfjsn52bc5687f3bf'
  }

  karate.log("Environment is ::: "+env+" Base URL is ::: "+ config.baseURL);
  karate.configure('readTimeout', 6000000);
  karate.configure("connectTimeout", 50000);

  return config;
}