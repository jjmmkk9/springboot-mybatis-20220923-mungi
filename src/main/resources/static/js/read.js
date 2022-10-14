


function getId(){
    let url = location.href;
    let id = url.substring(url.lastIndexOf("/") + 1);
    return id;
}

window.onload = () =>{
    //load 되자마자 request 해줍니다. => get요청이라서 - 당연함
    request();
}

function request(){

    $.ajax({
        //보내줄 데이터 없음
        async:false,
        type:"GET",
        url: "/api/news/" + getId(),
        dataType:"json",
        success: (response) => {
            console.log(response);
            setNewsData(response.data);
        },
        error: (error) => {
            console.log(error);
        }
    });
}

function setNewsData(news){
    const newsTitle = document.querySelector(".news-title");
    const newsBroadcasting = document.querySelector(".news-broadcasting");
    const newsWriter = document.querySelector(".news-writer");
    const newsCreateDate = document.querySelector(".news-create-date");
    const newsContent = document.querySelector(".news-content");
    const newsFiles = document.querySelector(".news-files");

    //하나씩 뿌려주기
    newsTitle.textContent = news.title;
    newsBroadcasting.textContent = news.broadcastingName;
    newsWriter.textContent = news.writer;
    newsContent.textContent = news.content;
    newsCreateDate.textContent = news.createDate;
    newsFiles.innerHTML = `
            <!-- href에 다운로드 요청 주소 넣어놓고, 텍스트는 파일이름(origin) -->
        <a href="/download/news?originFileName=${news.fileList[0].file_origin_name}&tempFileName=${news.fileList[0].file_temp_name}">${news.fileList[0].file_origin_name}</a>
    `;
}