const SUCCESS = "SUCCESS"
const FAILURE = "FAILURE"
const EXIST = "EXIST"

function bookmarkProcess() {
  const imgUrl = document.getElementById("imgUrl").src;
  const data = {imgUrl: imgUrl}

  fetch('/bookmark/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  })
  .then(response => response.text())
  .then(result => {
    const jsonData = JSON.parse(result);
    console.log(jsonData.code);
    console.log(jsonData.description);
    console.log(jsonData.data.value);
    const detail = jsonData.detail
    switch (jsonData.data.value) {
      case SUCCESS: {
        alert(detail);
        break;
      }
      case FAILURE : {
        alert(detail);
        break
      }
      case EXIST: {
        alert(detail);
        break
      }
    }
  })
}