#!/bin/bash

PROJECT="`pwd`"
FINAL="thegame"

LIBDIR="$PROJECT/lib"
LIBS=("jinput.jar" "lwjgl.jar" "slick.jar")

NATIVEDIR="$LIBDIR/native"

BINDIR="$PROJECT/bin"
BIN="com"

RESDIR="$PROJECT"
RES="res"

TMPJAR="`mktemp $PROJECT/main-XXXXXXXXXX.jar`"
TMPDIR="`mktemp -d $PROJECT/jar-XXXXXXXXXX`"

INIT="RunJar"
TMPINIT="$INIT.java"
MAIN="com.jtdev.thegame.Main"

INITJAR=(
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

cd $PROJECT
ant clean build
jar cf "$TMPJAR" -C "$BINDIR" "$BIN" -C "$RESDIR" "$RES"

cd $TMPDIR
jar xf "$TMPJAR"

for i in "${LIBS[@]}"; do
	   jar xf "$LIBDIR/$i"
done

cp -r "$NATIVEDIR" "$TMPDIR"

cd "$PROJECT"
rm -f "$TMPINIT"
touch "$TMPINIT"
for i in "${INITJAR[@]}"; do
	echo "$i" >> "$TMPINIT"
done
javac -cp "$TMPDIR" "$TMPINIT" -d "$TMPDIR"

cd "$TMPDIR"
jar cfe "$PROJECT/$FINAL.jar" "$INIT" `ls --color=auto "$TMPDIR"`
chmod +x "$PROJECT/$FINAL.jar"

cd "$PROJECT"
rm -rf "$TMPJAR" "$TMPDIR" "$TMPMANI" "$TMPINIT"
