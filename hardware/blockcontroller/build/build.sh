#!/usr/bin/env bash
cmake ..
make clean
make
make blockController-uno-upload
make blockController-uno-serial
