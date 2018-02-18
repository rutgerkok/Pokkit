#!/bin/bash
mvn clean
mvn package --fail-never dependency:go-offline --offline
