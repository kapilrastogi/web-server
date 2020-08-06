# web-server
Layout
 -- best to keep Dockerfile in empty directory with only files that needed to build image.

Docker container that runs Apache web server
 -- build the image (docker build -t my-apache2 .)

# Working with a MemcachedD container
 -- docker run --name some-memcache -p 11211:11211 -d memcached
 test connection to memcached
 * connect to memcached (telnet localhost 11211)
 * set MY_KEY 0 60 4
   pass
 * get MY_KEY

# How to link containers on a network
In order for containers to talk to each other, let's create a network
 -- docker network create kk-network

Run MemcacheD container on the created network
 -- docker run -d --restart=on-failure:3 --name kk-memcache --network kk-network memcached
