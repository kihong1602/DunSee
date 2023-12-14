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
  .then(result => resultProcess(result))
}

function removeProcess(imgUrl) {

  fetch("/bookmark/remove", {
    headers: {
      'Content-Type': 'application/json'
    },
    method: 'POST',
    body: JSON.stringify({
      imgUrl: imgUrl
    })
  }).then(response => response.text())
  .then(result => resultProcess(result))
}

function resultProcess(result) {
  const jsonData = JSON.parse(result);
  console.log(jsonData.code);
  console.log(jsonData.description);
  console.log(jsonData.data.value);
  const detail = jsonData.detail
  switch (jsonData.data.value) {
    case SUCCESS: {
      alert(detail);
      location.reload();
      break;
    }
    case FAILURE : {
      alert(detail);
      location.reload();
      break;
    }
    case EXIST: {
      alert(detail);
      break;
    }
  }
}

