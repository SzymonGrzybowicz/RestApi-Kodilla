#!/bin/bash

if ./runcrud.sh; then
    google-chrome http://localhost:8080/crud/v1/task/getTasks
else
    echo "blah blah"
fi