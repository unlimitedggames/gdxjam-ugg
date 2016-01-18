@echo off

rem 1. Download & install ImageMagick from http://www.imagemagick.org/script/binary-releases.php#windows. 
rem This is a command line utility to work with images. No ads, toolbars and other crap. It is freeware and open source.
rem You need file named ImageMagick-<version number>-windows-static.exe
rem 
rem 2. Create new file conv.bat with this snippet inside in a folder with images.
rem This will automatically trim all images in this folder and all its subfolders.

rem Settings :

rem Images extension
rem jp*g will match both .jpg's and .jpeg's
set extension=png

rem How much tolerance when matching color in percents
rem Larger tolerance = broader color range is regarded same and trimmed
rem Zero will mach only one exact color, use it for perfectly clean images in lossless formats (png, tif etc)
rem Use small value for clean images in jpegs, 1%%-5%% is a good start
set tolerance=4%%

rem More info on parameters - see http://www.imagemagick.org/Usage/crop/#trim

mkdir drawable-hdpi
mkdir drawable-mdpi
mkdir drawable-ldpi
for %%i in (*.%extension%) do ( 
	java -jar Resizer_1.3.3.jar %%i
)
ren drawable-hdpi 0.66
ren drawable-mdpi 0.416
ren drawable-ldpi 0.16
rd /Q /S ..\0.66
rd /Q /S ..\0.416
rd /Q /S ..\0.16
move /Y 0.66 ../
move /Y 0.416 ../
move /Y 0.16 ../
cd..
cd..
cd svgs
cd ShoeBoxFonts
call "Copy fnt.bat"
PAUSE