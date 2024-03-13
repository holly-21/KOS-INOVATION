# KOS-INOVATION
<br><br>

## Project Objective
전기차 충전소 위치 제공 및 선결제 서비스
<br><br>

## Project Period
2024.02.19 - 2024.03.11
<br><br>

## Project Rules
PR 충돌 방지를 위해 다음을 지켜주세요🙂
- ### <b>작업 시작 전 main repository에서 pull 한 뒤에 작업 시작</b>
- 작업 완료 후 main repository에 pull request 요청
  <br><br>

## Git repository 사용법
Git fork 후 branch 생성 과정
``` 
포크할 git 페이지(원git)을 포크하기
git clone [fork후 내 주소]
cd [클론한 폴더]
git remote -v
git remote add [remote git 별칭] [원격git 주소]
git checkout -b [브랜치명]
```

작업한 파일 fork한 개인 git에 올리는 법
```
git add [파일]
git commit -m “”
git push origin [브랜치명]
```

브랜치를 remote git의 main에 머지하는 방법
```
git checkout [브랜치명]
git rebase main
git checkout main
git merge [브랜치명]
git branch -d [브랜치명] # 머지한 후 브랜치가 더 이상 필요없어서 삭제할 경우 사용
```

git add,commit,push 취소<br>
`git reset HEAD [파일명]`<br>
참고 : [git CLI 취소 명령어](https://gmlwjd9405.github.io/2018/05/25/git-add-cancle.html)
<br><br>

> :bookmark: **REFERENCE** <br>
[ReadMe.md 문법 참고자료](https://gist.github.com/ihoneymon/652be052a0727ad59601)
