#!/usr/bin/.env bash

# Usage: buildAndPushImage PATH IMAGE VERSION


USAGE<<<END_USAGE
Usage: buildAndPushImage PATH IMAGE TAG REPO

  Where PATH is the path to the Dockerfile to build, IMAGE is the
  name of the image to build, TAG is the tag applied to the image,
  and REPO is the docker repository to push to.
END_USAGE

function printUsage() {
  echo "${USAGE}"
  exit 1
}

[ ${#} -ne 4 ] && printUsage

declare    path="${1}" image="${2}" tag="${3}" repo="${4}"

docker login
docker build -t "${image}:${tag}" "${path}"
docker tag "${image}:${tag}" "${repo}/${image}:${tag}"
docker push "${repo}/${image}:${tag}"
docker logout