@rem
setlocal
set PATH_FROM = E:\모딩툴\mindustry mod projects\NamelessArsenal
@rem
setlocal
set PATH_TO = C:\Users\user\AppData\Roaming\Mindustry\mods

if exist PATH_TO\mods\arsenal.jar del PATH_TO\mods\arsenal.jar
xcopy PATH_FROM\build\libs\arsenal.jar PATH_TO\mods\ /k /y


if exist PATH_TO\mods\ages.jar start C:\Users\user\Desktop\Mindustry.jar