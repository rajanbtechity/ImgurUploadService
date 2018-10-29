1. com.leadiq.imgur.resources package has ImgurProjectResource.java which has all end points.
2. ImgurProjectResource.java passes control to com.leadiq.imgur.handler package to handle and process the request.
3. Handler class finally calls webservice package class to make imgur Rest API call.
4. To upload image we can provide either desktop url or http url.
5. processUploadImage method in UploadImageHandler class calls another mathod getBase64encodedImage in the same class
to convert the image into Base64 format.
6. getBase64encodedImage checks for if url starts with "http:", if it starts with "http:" it uses inputStream
and URLConnection to download the image and convert it into Base64 Format.
Otherwise it reads the image file from desktop url and converts it into Base64 format.





How to test
1. Use Postman


2. First use POST  method to post images to imgur
  method=POST
  
  url=http://localhost:8041/ImgurUploadService/v1/images/upload(replace port)
    
  body=
 
 ==>Desktop File 
 {
"urls": [
"C:\\Users\\Rajan\\Desktop\\testimgurimage1.jpg"
]
}

==> http file(u can upload an image to imgur and then use the image link returned to upload it again)
{
"urls": [
"https://i.imgur.com/XIriPqh.jpg"
]
}


Output- Async Response

{
    "Job Status": "Success-Image Upload processing in background"
}


3. In postman open another tab and use GET method

 method = GET
 url=http://localhost:8041/ImgurUploadService/v1/images(replace port)

output- List of image url

Sample Response
{
    "uploaded": [
        "https://i.imgur.com/FeQlKE0.jpg",
        "https://i.imgur.com/tg0ubjy.jpg"
    ]
}