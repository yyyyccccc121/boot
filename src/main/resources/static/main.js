          var recorder;  
            var audio = document.querySelector('audio');  
              
            function startRecording() {  
               if(recorder){
                   recorder.start();
                   return;
               }
               
                HZRecorder.get(function (rec) {  
                    recorder = rec;  
                    recorder.start();  
                },{error:showError});  
            }  
              
              
            function obtainRecord(){  
                 if(!recorder){
                    showError("请先录音");
                    return;
                }
               var record = recorder.getBlob();  
               if(record.duration!==0){
               downloadRecord(record.blob);
               }else{
                   showError("请先录音")
               }
            };  

            function downloadRecord(record){
              var save_link = document.createElementNS('http://www.w3.org/1999/xhtml', 'a')
                save_link.href = URL.createObjectURL(record);
                var now=new Date;
                save_link.download = now.Format("yyyyMMddhhmmss");
                fake_click(save_link);
            }

       
            function fake_click(obj) {
            var ev = document.createEvent('MouseEvents');
            ev.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
            obj.dispatchEvent(ev);
            }

                 function getStr(){
              var now=new Date;
              var str= now.toDateString();
            }

            function stopRecord(){  
                recorder&&recorder.stop();  
            };  
            var msg={};
            //发送音频片段
            var msgId=1;
            function send(){
                if(!recorder){
                    showError("请先录音");
                    return;
                }

               var data=recorder.getBlob();
               if(data.duration==0){
                     showError("请先录音");
                    return;
               }
                msg[msgId]=data;
                recorder.clear();
                console.log(data);

                // var data1 = JSON.stringify(data);
                var blob1 = data.blob;
                console.log("blob1="+blob1);
                blobToDataURI(blob1);
                // console.log("base1="+base1);
                //----------分割线-----------
                // $.ajax({
                //     url: "test/qq",
                //     data: {"base1":base1},
                //     success:function(data) {
                //         var base2 = data.theBase;
                //         console.log("base2="+base2);
                //         var blob2 =  dataURItoBlob(base2);
                //         console.log("blob2="+blob2);
                //
                //         var dur=10;
                //         var str="<audio id='asd' controls autoplay></audio>";
                //         $(".messages").append(str);
                //         document.getElementById("#asd").src = window.URL.createObjectURL(blob1);
                //     },
                //     error:function () {
                //         alert("出错");
                //     },
                //     dataType: "json",
                //     type: "POST"
                // });
                //----------分割线-----------

                var dur=data.duration/10;
                 var str="<div class='warper'><div id="+msgId+" class='voiceItem'>"+dur+"s</div></div>"
                $(".messages").append(str);
                msgId++;
            }
            
            $(document).on("click",".voiceItem",function(){
                var id=$(this)[0].id;
                var data=msg[id];
                playRecord(data.blob);
            })

            var ct;
            function showError(msg){
                $(".error").html(msg);
                clearTimeout(ct);
                ct=setTimeout(function() {
                    $(".error").html("")
                }, 3000);
            }


            function playRecord(blob){  
                if(!recorder){
                    showError("请先录音");
                    return;
                }
                 recorder.play(audio,blob);  
            };  
              
            /* 视频 */  
            function scamera() {  
                var videoElement = document.getElementById('video1');  
                var canvasObj = document.getElementById('canvas1');  
                var context1 = canvasObj.getContext('2d');  
                context1.fillStyle = "#ffffff";  
                context1.fillRect(0, 0, 320, 240);  
                context1.drawImage(videoElement, 0, 0, 320, 240);  
            };  
              
            function playVideo(){  
                var videoElement1 = document.getElementById('video1');  
                var videoElement2 = document.getElementById('video2');  
                videoElement2.setAttribute("src", videoElement1);  
            };
           
           function base64ToBlob(theBase) {
               var arr = theBase.split(','), mime = arr[0].match(/:(.*?);/)[1],
                   bstr = atob(arr[1]),n = bstr.length,u8arr = new Uint8Array(n);
               while (n--){
                   u8arr[n]=bstr.charCodeAt(n);
               }
               return new Blob([u8arr],{type:mime});
           }

          function dataURItoBlob(base64Data) {
              //console.log(base64Data);//data:image/png;base64,
              var byteString;
              if(base64Data.split(',')[0].indexOf('base64') >= 0)
                  byteString = atob(base64Data.split(',')[1]);//base64 解码
              else{
                  byteString = unescape(base64Data.split(',')[1]);
              }
              var mimeString = base64Data.split(',')[0].split(':')[1].split(';')[0];//mime类型 -- image/png

              // var arrayBuffer = new ArrayBuffer(byteString.length); //创建缓冲数组
              // var ia = new Uint8Array(arrayBuffer);//创建视图
              var ia = new Uint8Array(byteString.length);//创建视图
              for(var i = 0; i < byteString.length; i++) {
                  ia[i] = byteString.charCodeAt(i);
              }
              var blob = new Blob([ia], {
                  type: mimeString
              });
              return blob;
          }

          function blobToDataURI(blob) {
              var a = new FileReader();
              a.readAsDataURL(blob);
              a.onload = function () {
                  var theBase = a.result;
                  console.log("theBase="+theBase);
                  //----------分割线-----------
                  $.ajax({
                      url: "test/qq",
                      data: {"base1":theBase},
                      success:function(data) {
                          var base2 = data.theBase;
                          console.log("base2="+base2);
                          var blob2 =  dataURItoBlob(base2);
                          console.log("blob2="+blob2);

                          var dur=10;

                          var str="<audio id='asd' controls autoplay src=''></audio>";
                          $(".messages").append(str);
                          document.getElementById("asd").src = window.URL.createObjectURL(blob2);
                      },
                      error:function () {
                          alert("出错");
                      },
                      dataType: "json",
                      type: "POST"
                  });
                  //----------分割线-----------

              }
          }