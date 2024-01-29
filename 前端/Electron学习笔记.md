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

## 3. 打开文件并显示

```
npm install @electron/remote --save
```

* 启用remote

```js
const { app, BrowserWindow } = require('electron');
const path = require('node:path');

const createWindow = () => {
    const win = new BrowserWindow({
        icon: __dirname + '/public/markdown.png',
        width: 1400,
        height: 800,
        webPreferences: {
            nodeIntegration: true,// 开启进程使用node
            enableRemoteModule: true, // 允许remote
            contextIsolation: false, // 解决html的require
        },
    })
    // 开发者模式
    // win.webContents.openDevTools()

    win.loadFile('index.html')
    win.on('closed', () => {
        mainWindow = null
    })

    require('@electron/remote/main').initialize()
    require("@electron/remote/main").enable(win.webContents)
    require('./src/ipcMain.js');
}

app.whenReady().then(() => {
    createWindow()
})

app.on('window-all-closed', () => {
    if (process.platform !== 'darwin') {
        app.quit()
    }
})
```


* 窗口打开文件

```js
const { ipcRenderer } = require('electron');
const dialog = require('@electron/remote').dialog
const path = require('path')

ipcRenderer.on('action', function (event, action) {
    switch (action) {
        case "new":
            console.log("new");
            break;
        case "open":
            dialog.showOpenDialog({
                // defaultPath: __dirname,
                buttonLabel: '打开',//确认按钮文字修改
                title: "请选择Markdown文件",//打开对话框的标题
                properties: ['openFile'],//'openDirectory'只打开目录 multiSelections多选
                filters: [
                    {
                        name: 'MarkDown',
                        extensions: ['md', 'markdown'] //扩展名
                    }
                ]
            }).then((ret) => {
                let fsData = fs.readFileSync(ret.filePaths[0], 'UTF-8');
                renderMarkdown(fsData, path.dirname(ret.filePaths[0]))
            })
            break;
        case "save":
            break;
    }

})
```

## 4. 获取命令行参数

```js
const { app } = require("electron");
const remote = require("@electron/remote");
const fs = require("fs");
// const path = require('path')

console.log(remote.process.argv);

// 获取命令行参数
let args = remote.process.argv;

window.onload = function () {
  if (args.length >= 2) {
    const filePath = args[1];
    console.log(filePath);
    fs.stat(filePath, (err, data) => {
      if (data.isFile && filePath.endsWith(".md")) {
        let fsData = fs.readFileSync(filePath, "UTF-8");
        renderMarkdown(fsData, path.dirname(filePath));
      } else {
        renderMarkdown("", "");
      }
    });
  } else {
    renderMarkdown("", "");
  }
};
```
















