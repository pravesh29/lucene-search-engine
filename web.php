
<!DOCTYPE html>
<html>
<head>
    <title>Bootstrap Form Validation</title>

    <!-- Include the FontAwesome CSS if you want to use feedback icons provided by FontAwesome -->
    <!--<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" />-->
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-sm-8 col-sm-offset-2">
                <div class="page-header">
                    <h2>Lucene in Action!</h2>
                </div>

                <form id="defaultForm" method="post" class="form-horizontal" >
                    <div class="form-group">
                            <div class="col-sm-12">
                                <input type="text" class="form-control" id="searchstr" name="search" placeholder="Enter a string here" />
                            </div>
                    </div>

                    <div class="form-group">
<div class="col-md-6 center-block"> 
<button id="search" name="singlebutton" class="btn btn-primary">Hit me!</button> 
</div>
</div>
<div class="form-group">
<div id="div1"> 

</div>
                    </div>
                </form>
            </div>
        </div>
    </div>

<script type="text/javascript">
$("#search").click(function(e){
    $.ajax({
  type: "POST",
  url: "http://localhost/test.php",
  data: { 'qs' : $('#searchstr').val()},
  dataType: "text",
  success: function (response) {
           // you will get response from your php page (what you echo or print)     
           console.log(response);
           $("#div1").html(response);   

        },
        error: function(jqXHR, textStatus, errorThrown) {
           console.log(textStatus, errorThrown);
        }

});
        e.preventDefault();


});
</script>
</body>
</html>
