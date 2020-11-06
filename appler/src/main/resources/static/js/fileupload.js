function checkImageType(fileName){
    var pattern = /jpg|gif|png|jpeg/i;
    return fileName.match(pattern);
    }
function getFileInfo(fullName){
       var fileName, imgsrc, getLink, fileLink;
       if(checkImageType(fullName)){
       imgsrc = "/spring02/upload/displayFile?fileName="+fullName;
       console.log(imgsrc);
       fileLink = fullName.substr(14);
       console.log(fileLink);
       var front = fullName.substr(0, 12);
       console.log(front);
       var end = fullName.substr(14);
       console.log(end);
       getLink = "/spring02/upload/displayFile?fileName="+front+end;
       console.log(getLink);
       }
        else {
        fileLink=fullName.substr(12);
        console.log(fileLink);
        getLink="/spring02/upload/displayFile?fileName="+fullName;
        console.log(getLink);
        }
        fileName =fileLink.substr(fileLink.indexOf("_")+1);
        console.log(fileName);
        return {fileName:fileName, imgsrc:imgsrc, getLink:getLink, fullName:fullName};
  }