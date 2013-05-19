#!/bin/bash

PROJECT="`pwd`"
PLATFORM="linux"

LIBDIR="$PROJECT/lib"
LIBS=("jinput.jar" "lwjgl.jar" "slick.jar")

NATIVEDIR="$LIBDIR/native/$PLATFORM"
NATIVES=("libjinput-linux64.so" "libjinput-linux.so" "liblwjgl64.so" "liblwjgl.so" "libopenal64.so" "libopenal.so")

BINDIR="$PROJECT/bin"
BIN="com"

RESDIR="$PROJECT"
RES="res"

TMPJAR="`mktemp $PROJECT/main-XXXXXXXXXX.jar`"
TMPDIR="`mktemp -d $PROJECT/jar-XXXXXXXXXX`"

#INIT="RunJar"
#TMPINIT="$INIT.java"
#MAIN="com.jtdev.thegame.Main"
#INITJAR=(
#"//package RunJar;"
#"import $MAIN;"
#"import org.newdawn.slick.SlickException;"
#"public class RunJar {"
#"	public RunJar() {"
#"	}"
#"	public static void main(String[] args) throws SlickException {"
#"		String newpath = \"lib/native/$PLATFORM\";"
#"		System.setProperty(\"java.library.path\", newpath);"
#"		Main main = new Main();"
#"		main.main(args);"
#"	}"
#"}")

MAIN="com.jtdev.thegame.Main"

echo "${NATIVES[@]}"

cd $PROJECT
ant clean build
jar cf "$TMPJAR" -C "$BINDIR" "$BIN" -C "$RESDIR" "$RES"

cd $TMPDIR
jar xf "$TMPJAR"

for i in "${LIBS[@]}"; do
	   jar xf "$LIBDIR/$i"
done

for i in "${NATIVES[@]}"; do
	cp "$NATIVEDIR/$i" "$TMPDIR"
done

#cd "$PROJECT"
#rm -f "$TMPINIT"
#touch "$TMPINIT"
#for i in "${INITJAR[@]}"; do
	#echo "$i" >> "$TMPINIT"
#done
#javac -cp "$TMPDIR" "$TMPINIT" -d "$TMPDIR"

jar cfe "$PROJECT/game.jar" "$MAIN" `ls --color=auto "$TMPDIR"`

cd "$PROJECT"
rm -rf "$TMPJAR" "$TMPDIR" "$TMPMANI" "$TMPINIT"
