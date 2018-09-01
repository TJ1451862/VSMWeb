<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2018/8/28
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>向量空间模型</title>
</head>
<body>
<h1 style="text-align: center">向量空间模型</h1>

<form style="text-align: center" >
  <label >查询:</label>
  <input type="text"  name="query" id="query" value="" />
  <input type="button" name="submit" value="确定" onclick="getQuery()"/>
  <input type="button" name="result" value="查询结果" onclick="outputResult()"/>
  <input type="reset" value="重置" name="reset" />
</form>

<div style="text-align: center">
  <table>
    <tbody>
    <tr>
      <td style="text-align: center"><h3>文档集处理</h3></td>
      <td style="text-align: center"><h3>处理查询</h3></td>
      <td style="text-align: center"><h3>处理文档</h3></td>
      <td style="text-align: center"><h3>查询</h3></td>
    </tr>

    <tr>
      <td valign="top"
        <div id="docSet">
          <ol>
            <li><input type="button" name="segmentQuery" value="建立倒排索引" onclick="createIndex()"/></li></br>
            <li><input type="button" name="segmentQuery" value="求idf" onclick="countIdf()"/></li></br>
          </ol>
        </div>
      </td>

      <td valign="top">
        <div id="question">
          <ol>
            <li><input type="button" name="segmentQuery" value="中文分词" onclick="segmentQuery()"/></li></br>
            <li><input type="button" name="segmentQuery" value="去标点" onclick="removePunctuation()"/></li></br>
            <li><input type="button" name="segmentQuery" value="去停用词" onclick="removeStopWords()"/></li></br>
            <li><input type="button" name="segmentQuery" value="求tf" onclick="countTf()"/></li></br>
            <li><input type="button" name="segmentQuery" value="求查询向量" onclick="outPutVector()"/></li></br>

          </ol>
        </div>
      </td>

      <td valign="top">
        <div id="doc">

          <ol>
            <li><input type="button" name="segmentQuery" value="求各文档tf" onclick="outPutEveryDoc_Tf()"/></li></br>
            <li><input type="button" name="segmentQuery" value="求文档向量" onclick="outPutEveryDoc_Vector()"/></li></br>
          </ol>
        </div>
      </td>

      <td valign="top">
        <div id="runquery">
          <ol>
            <li><input type="button" name="segmentQuery" value="计算查询与各个文档的相似度" onclick="countSimilarities()"/></li></br>
            <li><input type="button" name="segmentQuery" value="按相似度降序对文档排序,并输出查询结果" onclick="sortSimilarities()"/></li></br>
          </ol>
        </div>
      </td>

    </tr>

    </tbody>
  </table>
</div>
<form style="text-align: center" >
  <label><h2>结果演示</h2></label><br/>
  <textarea id="result" cols="150" rows="15"></textarea>
</form>

<script type="text/javascript">

    function createIndex(){
        var request=new XMLHttpRequest();
        request.open("POST","createIndex",true);
        request.onreadystatechange=function () {
            if(request.readyState==4&&request.status==200){
                console.log(request.responseText);
                document.getElementById("result").innerHTML=request.responseText;
            }
        }
        request.send();
    }

    function countIdf(){
        var request=new XMLHttpRequest();
        request.open("POST","countIdf",true);
        request.onreadystatechange=function () {
            if(request.readyState==4&&request.status==200){
                console.log(request.responseText);
                document.getElementById("result").innerHTML=request.responseText;
            }
        }
        request.send();
    }

    function getQuery() {
        var request=new XMLHttpRequest();
        request.open("GET","getQuery?query="+document.getElementById("query").value,true);
        request.onreadystatechange=function () {
            if(request.readyState==4&&request.status==200){
                console.log(request.responseText);
                document.getElementById("result").innerHTML=request.responseText;
            }
        }
        request.send();

    };

    function segmentQuery(){
        var request=new XMLHttpRequest();
        request.open("POST","segmentQuery",true);
        request.onreadystatechange=function () {
            if(request.readyState==4&&request.status==200){
                console.log(request.responseText);
                document.getElementById("result").innerHTML=request.responseText;
            }
        }
        request.send();
    }

    function removePunctuation() {
        var request=new XMLHttpRequest();
        request.open("POST","removePunctuation",true);
        request.onreadystatechange=function () {
            if(request.readyState==4&&request.status==200){
                console.log(request.responseText);
                document.getElementById("result").innerHTML=request.responseText;
            }
        }
        request.send();
    }

    function removeStopWords() {
        var request=new XMLHttpRequest();
        request.open("POST","removeStopWords",true);
        request.onreadystatechange=function () {
            if(request.readyState==4&&request.status==200){
                console.log(request.responseText);
                document.getElementById("result").innerHTML=request.responseText;
            }
        }
        request.send();
    }

    function countTf() {
        var request=new XMLHttpRequest();
        request.open("POST","countTf",true);
        request.onreadystatechange=function () {
            if(request.readyState==4&&request.status==200){
                console.log(request.responseText);
                document.getElementById("result").innerHTML=request.responseText;
            }
        }
        request.send();
    }

    function setVector() {
        var request=new XMLHttpRequest();
        request.open("POST","setVector",true);
        request.onreadystatechange=function () {
            if(request.readyState==4&&request.status==200){
                console.log(request.responseText);
                document.getElementById("result").innerHTML=request.responseText;
            }
        }
        request.send();
    }

    function outPutVector() {
        var request=new XMLHttpRequest();
        request.open("POST","outPutVector",true);
        request.onreadystatechange=function () {
            if(request.readyState==4&&request.status==200){
                console.log(request.responseText);
                document.getElementById("result").innerHTML=request.responseText;
            }
        }
        request.send();
    }

    function outPutEveryDoc_Tf(){
        var request=new XMLHttpRequest();
        request.open("POST","outPutEveryDoc_Tf",true);
        request.onreadystatechange=function () {
            if(request.readyState==4&&request.status==200){
                console.log(request.responseText);
                document.getElementById("result").innerHTML=request.responseText;
            }
        }
        request.send();
    }

    function outPutEveryDoc_Vector(){
        var request=new XMLHttpRequest();
        request.open("POST","outPutEveryDoc_Vector",true);
        request.onreadystatechange=function () {
            if(request.readyState==4&&request.status==200){
                console.log(request.responseText);
                document.getElementById("result").innerHTML=request.responseText;
            }
        }
        request.send();
    }

    function countSimilarities(){
        var request=new XMLHttpRequest();
        request.open("POST","countSimilarities",true);
        request.onreadystatechange=function () {
            if(request.readyState==4&&request.status==200){
                console.log(request.responseText);
                document.getElementById("result").innerHTML=request.responseText;
            }
        }
        request.send();
    }

    function sortSimilarities(){
        var request=new XMLHttpRequest();
        request.open("POST","sortSimilarities",true);
        request.onreadystatechange=function () {
            if(request.readyState==4&&request.status==200){
                console.log(request.responseText);
                document.getElementById("result").innerHTML=request.responseText;
            }
        }
        request.send();
    }

    function outputResult(){
        var request=new XMLHttpRequest();
        request.open("POST","outputResult",true);
        request.onreadystatechange=function () {
            if(request.readyState==4&&request.status==200){
                console.log(request.responseText);
                document.getElementById("result").innerHTML=request.responseText;
            }
        }
        request.send();
    }


</script>
</body>
</html>


