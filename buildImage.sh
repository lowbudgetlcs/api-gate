#!/usr/bin/env bash

# Usage: buildAndPushImage VERSION

declare image=api_gate:"${1}"
declare tag=lblcs/api_gate:"${1}"

docker build -t "${image}" .
docker tag "${image}" "${tag}"
docker push "${tag}"