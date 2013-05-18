#!/bin/sh

java -cp .:lib/slick.jar:lib/lwjgl.jar:lib/jinput.jar:./bin -Djava.library.path=lib/native/linux com.jtdev.thegame.Main
