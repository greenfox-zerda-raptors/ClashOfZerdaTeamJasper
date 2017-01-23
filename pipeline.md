**Continous integration and cloud based app deployment.**


Register an account on the following websites:

<a>https://github.com/</a> <br>
<a>https://travis-ci.org/</a><br>
<a>https://heroku.com/apps</a><br>

- Link your repos to travis on the website (if you dont have any create a repository under your github account)

- Create a .travis.yml file in your project root directory.
Customize .travis.yml to handle your dependecies.

- Push .travis.yml to github.
Push any changes to github to trigger a build on travis.

- Download and install Heroku CLI from the website.
It is possible to create a Procfile in your project root directory in case you need it to handle any exceptions in
a custom manner but it did everything right for me by default.

- Navigate to your project folder in git bash (or command shell) and type:<br>
$heroku create<br>
$git push heroku master  (in case you're using any other branches than master $git push heroku yourbarnch:master otherwise it won't build)<br>
$heroku open

- On heroku website you can click on your existing apps to create a pipeline, this will allow you to push directly to github,
build on travis and in case the tests pass deploy automatically to heroku (pretty self explanatory once you're there)

- To add addons (forxmpl postgreSQL to handle databases):<br>
$heroku addons:create heroku-postgresql:hobby-dev<br>
$heroku pg:info
		