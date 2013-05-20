#!/bin/bash

PROJECT="`pwd`"
PLATFORM="linux"

LIBDIR="$PROJECT/lib"
LIBS=("jinput.jar" "lwjgl.jar" "slick.jar")

#NATIVEDIR="$LIBDIR/native/$PLATFORM"
NATIVEDIR="$LIBDIR/native"
NATIVES=("libjinput-linux64.so" "libjinput-linux.so" "liblwjgl64.so" "liblwjgl.so" "libopenal64.so" "libopenal.so")

BINDIR="$PROJECT/bin"
BIN="com"

RESDIR="$PROJECT"
RES="res"

TMPJAR="`mktemp $PROJECT/main-XXXXXXXXXX.jar`"
TMPDIR="`mktemp -d $PROJECT/jar-XXXXXXXXXX`"

INIT="RunJar"
TMPINIT="$INIT.java"
MAIN="com.jtdev.thegame.Main"

PATHS=""
for i in "${NATIVES[@]}"; do
	PATHS=$PATHS" System.load(System.getProperty(\"java.class.path\") + \"!\" + File.separator + \"`echo "$i" | sed "s/[/]?\(.*\)lib\(.*\).so/\\1\\2/g"`\");"
done

INITJAR=(
"//package $INIT;"
"import com.jdotsoft.jarloader.JarClassLoader;"
"public class $INIT {"
"	public static void main(String[] args) {"
"		JarClassLoader jcl = new JarClassLoader();"
"		try {"
"			jcl.invokeMain(\"$MAIN\", args);"
"		} catch (Throwable e) {"
"			e.printStackTrace();"
"		}"
"	}"
"}"
)

echo "${NATIVES[@]}"

cd $PROJECT
ant clean build
jar cf "$TMPJAR" -C "$BINDIR" "$BIN" -C "$RESDIR" "$RES"

cd $TMPDIR
jar xf "$TMPJAR"

for i in "${LIBS[@]}"; do
	   jar xf "$LIBDIR/$i"
done

#for i in "${NATIVES[@]}"; do
#	cp "$NATIVEDIR/$i" "$TMPDIR"
#done

cp -r "$NATIVEDIR" "$TMPDIR"

cd "$PROJECT"
rm -f "$TMPINIT"
touch "$TMPINIT"
for i in "${INITJAR[@]}"; do
	echo "$i" >> "$TMPINIT"
done
javac -cp "$TMPDIR" "$TMPINIT" -d "$TMPDIR"

cd "$TMPDIR"
#jar cfe "$PROJECT/game.jar" "$MAIN" `ls --color=auto "$TMPDIR"`
jar cfe "$PROJECT/game.jar" "$INIT" `ls --color=auto "$TMPDIR"`

cd "$PROJECT"
rm -rf "$TMPJAR" "$TMPDIR" "$TMPMANI" "$TMPINIT"
