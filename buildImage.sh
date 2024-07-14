#!/usr/bin/env bash

# Usage: buildAndPushImage VERSION

declare image=tournament_engine:"${1}"
declare tag=lblcs/tournament_engine:"${1}"

docker build -t "${image}" .
docker tag "${image}" "${tag}"
docker push "${tag}"