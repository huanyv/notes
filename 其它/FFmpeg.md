# FFmpeg

* 下载地址：<https://ffmpeg.org/download.html#build-windows>

```shell
# 视频转GIF（格式转换）
ffmpeg -i input.mp4 output.gif
# GIF裁剪
# aa目标宽度 bb目标高度 cc裁剪横向起始位置（左上） dd裁剪纵向起始位置（左上）
ffmpeg -i 202504151113a.gif -vf "crop=aa:bb:cc:dd" output.gif
# 提取视频音频
ffmpeg -i input.mp4 -q:a 0 -map a output.mp3
# 视频剪辑
ffmpeg -i input.mp4 -ss 10 -to 20 -c copy output.mp4
# 合并视频
# 创建一个名为 files.txt 的文本文件，内容为：
# file 'input1.mp4'
# file 'input2.mp4'
ffmpeg -f concat -safe 0 -i files.txt -c copy output.mp4
```

