[![Build Status](https://travis-ci.org/openmastery/dictionary.svg?branch=master)](https://travis-ci.org/openmastery/dictionary)

# dictionary

## Heroku

Install heroku cli (google)

Log into heroku

`heroku login`

Create the heroku application

`heroku create openmastery-dictionary`

Or, if the application has already been created, initialize the remote

`heroku git:remote -a openmastery-dictionary`

Create the database (can upgrade to hobby-basic just by associating credit card w/ account)

`heroku addons:create heroku-postgresql:hobby-dev`

Deploy the application on heroku

`git push heroku master`
