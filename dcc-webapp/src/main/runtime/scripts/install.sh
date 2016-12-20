#!/usr/bin/env bash

if [ -f /etc/init.d/dccweb ]
then
    echo "Uninstalling Old Service"
    rm /etc/init.d/dccweb
fi

DCCJAR="$(readlink -f ./lib/*.jar)"
echo "Path to jar: ${DCCJAR}"

ln -s ${DCCJAR} /etc/init.d/dccweb
chmod 755 /etc/init.d/dccweb

