# Electron学习笔记

## 1. 第一个项目

* 官网文档：<https://www.electronjs.org/zh/docs/latest/>
* 建议使用比较新的node版本
* 切换国内源，在项目目录，添加`.npmrc`文件

```
electron_mirror=https://npmmirror.com/mirrors/electron/
```

* 创建项目

```
mkdir my-electron-app && cd my-electron-app
npm init
```

* 在init过程中`entry point`应为`main.js`
* `author`与`description`可为任意值，但对于应用打包是必填项。

```
npm install --save-dev electron
```

* 在`package.json`添加script命令

```
{
  "scripts": {
    "start": "electron ."
  }
}
```

* 项目目录下新建文件`main.js`

```js
const { app, BrowserWindow } = require('electron')

// 修改已有的 createWindow() 方法
const createWindow = () => {
  const win = new BrowserWindow({
    width: 800,
    height: 600
  })

  win.loadFile('index.html')
}
// ...
```

* 运行`npm start`
* 打包部署

```
# 安装脚手架
npm install --save-dev @electron-forge/cli
npx electron-forge import

# 打包
npm run make
```

## 2. 读取文件

* main.js

```js
const { app, BrowserWindow } = require('electron')
const path = require('node:path')

const createWindow = () => {
    const win = new BrowserWindow({
        width: 800,
        height: 600,
        webPreferences: {
            preload: path.join(__dirname, 'preload.js'),
            // 开启进程使用node
            nodeIntegration: true
        }
    })
    // 开发者模式
    // win.webContents.openDevTools();
    win.loadFile('index.html')
    win.on('closed',()=>{
        mainWindow = null
    })
}
app.whenReady().then(() => {
    createWindow()
})
```

* index.html

```html
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8" />
  <!-- https://developer.mozilla.org/zh-CN/docs/Web/HTTP/CSP -->
  <meta http-equiv="Content-Security-Policy" content="default-src 'self'; script-src 'self'" />
  <title>你好!</title>
</head>

<body>
  <h1>你好!</h1>
  我们正在使用 Node.js <span id="node-version"></span>, Chromium
  <span id="chrome-version"></span>, 和 Electron
  <span id="electron-version"></span>.

  <button id="btn">读取文件内容</button>
  <div id="content"></div>
  <!-- <script src="./render/index.js"></script> -->
</body>

</html>
```


* preload.js

```js
var fs = require('fs');
window.onload = function () {
    var btn = this.document.querySelector('#btn')
    var content = this.document.querySelector('#content')
    btn.onclick = function () {
        fs.readFile('file.txt', (err, data) => {
            content.innerHTML = data
        })
    }
} 
```

## 3. 打开文件

```
npm install @electron/remote --save
```






















