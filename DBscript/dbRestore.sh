#!/usr/bin/env bash
echo "Restoring Users"
mongo localhost:27017/manuals ./categories.js
echo "##categories Restored##"
mongo localhost:27017/manuals ./company.js
echo "##Company Restored##"
mongo localhost:27017/manuals ./product.js
echo "##Product Restored##"
mongo localhost:27017/manuals ./users.js
echo "##Users Restored##"

mongo manuals --eval "db.product.find().forEach(function(o) {print(o._id);})"

curl -X POST http://localhost:8888/rest/file/uploadFile \
  -H 'Content-Type: multipart/form-data' \
  -H 'Postman-Token: b1726a05-bc87-4913-af2e-f6472cafd36d' \
  -H 'cache-control: no-cache' \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -F 'ProductId=5c054d892b18d70de06daf2f' \
  -F 'file=@./images/iphon.jpg'

curl -X POST http://localhost:8888/rest/file/uploadFile \
  -H 'Content-Type: multipart/form-data' \
  -H 'Postman-Token: b1726a05-bc87-4913-af2e-f6472cafd36d' \
  -H 'cache-control: no-cache' \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -F 'ProductId=5c07c9015dea70acb0676830' \
  -F 'file=@./images/hawai.jpg'

echo "Iphone image uploaded"