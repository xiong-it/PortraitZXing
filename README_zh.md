# PortraitZXing
zxing竖屏解决方案，且最低兼容api 9。

#项目介绍
google团队提供的zxing扫描Android客户端默认兼容api 15以上手机，并且是横屏扫描，目前而言，3.0以下手机仍然占有相当少一部分市场，所以兼容3.0手机仍然是部分公司的困.
</br>本项目向下兼容至api 9.
</br>再者，许多app的主流使用场景是竖屏，所以竖屏扫描也是大多开发者的需求。
</br>PortraitZXing包含横屏和竖屏扫描客户端，并且都向下兼容至 api 9.具体请看目录介绍.
</br>开发者所需做的事情主要是根据自身需求精简代码。

#目录介绍
Eclipse：供使用Eclipse作为IDE的开发者使用</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-|ZXingLib：zxing二维码扫描类库，整合google团队的zxing类库的android-core与core，源码未做任何修改，请先导入此工程，并设置为library</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-|LandscapeZXing：zxing二维码扫描客户端源码，将原版zxing二维码扫描器，向下兼容至api 9（Android 2.3），仍然是横屏扫描</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-|PortraitZXing:zxing二维码扫描客户端竖屏版源码，将原版zxing二维码扫描器，向下兼容至api 98（Android 2.3），并且默认为竖屏扫描</br>
</br>
AndroidStudio:供使用Android Studio作为IDE的开发者使用</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-|PortraitZXing:root module，导入后有3个子module.</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-|zxinglibrary：library module.zxing二维码扫描类库，整合google团队的zxing类库的android-core与core，源码未做任何修改</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-|landscapezxing：landscape client module.zxing二维码扫描客户端源码，将原版zxing二维码扫描器，向下兼容至api 9（Android 2.3），仍然是横屏扫描</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-|portraitzxing：portrait client module.zxing二维码扫描客户端竖屏版源码，将原版zxing二维码扫描器，向下兼容至api 9（Android 2.3），并且默认为竖屏扫描</br>

#changelog</br>
v1.0(2016/06/10)</br>
library based on v3.2.1</br>
client based on v4.7.6(106)
