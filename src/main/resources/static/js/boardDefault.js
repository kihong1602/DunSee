function writePermissionVerification(boardType) {
  let isAuthenticated = document.getElementById('isAuthenticated').value;
  console.log("여기 오나?");
  console.log(isAuthenticated);

  if (isAuthenticated === "false") {
    alert('로그인이 필요한 기능입니다.');
    console.log(isAuthenticated);
    location.href = '/login';
  } else {
    location.href = '/board/' + boardType + '/write';
    console.log(isAuthenticated);
  }
}