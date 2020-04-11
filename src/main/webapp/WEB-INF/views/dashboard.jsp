<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>An�lise de dados</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css">
    <link href="assets/vendor/fonts/circular-std/style.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/libs/css/style.css">
    <link rel="stylesheet" href="assets/vendor/fonts/fontawesome/css/fontawesome-all.css">
    <link rel="stylesheet" href="assets/vendor/datepicker/tempusdominus-bootstrap-4.css">
    <link rel="stylesheet" href="assets/vendor/inputmask/css/inputmask.css">
</head>

<body>
    <!-- ============================================================== -->
    <!-- main wrapper -->
    <!-- ============================================================== -->
    <div class="dashboard-main-wrapper">
        <div class="dashboard-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xl-10">
                        <!-- ============================================================== -->
                        <!-- pageheader  -->
                        <!-- ============================================================== -->
                        <!-- ============================================================== -->
                        <!-- end pageheader  -->
                        <!-- ============================================================== -->
                        <div class="page-section" id="overview">
                            <!-- ============================================================== -->
                            <!-- overview  -->
                            <!-- ============================================================== -->
                            <div class="row">
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                    <h2>Introdu��o</h2>
                                    <p class="">
                                    	A presente aplica��o trata-se de uma ferramenta online, a qual tem como principal
                                    	objetivo fornecer aux�lio a ps�cologos e psiquiatras no processo de diagn�stico de 
                                    	transtornos de ansiedade. Dessa forma, a ferramenta � consitu�da das seguintes etapas:
                                    </p>
                                    <ul class="list-unstyled arrow">
                                        <li>
                                        	1 - Selecionar sintomas: primeiramente, � preciso selecionar todos os sintomas
                                        	correspondes ao paciente.
                                        </li>
                                        <li>2 - Informar o tempo dos sintomas: ap�s selecionar os sintomas, � necess�rio nessa etapa
                                        que seja informado quanto tempo, em meses, em que o paciente vem percebendo os sintomas em seu cotidiano.
                                        </li>
                                        <li>3 - Selecionar as situa��es: consiste em selecionar as principais situa��es nas quais 
                                        os indicadores sintom�ticos tornam-se percept�veis para o paciente.
                                        </li>
                                        <li>4 - Ao clicar em "Analisar Dados", voc� ser� direcionado para outr� p�gina, onde esta
                                        apresenta um conjunto de poss�veis transtornos e comorbidades que podem estar associadas ao paciente em quest�o.
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <!-- ============================================================== -->
                            <!-- end overview  -->
                            <!-- ============================================================== -->
                        </div>
                        <!-- ============================================================== -->
                        <!-- basic form  -->
                        <!-- ============================================================== -->
                        <div class="row">

                        </div>
                        <div class="row">
                        <form action="analisar" method="post">
                            <div class="col-xl-12" id="checkboxradio">
                                <div class="section-block">
                                    <h3 class="section-title">Fase 1 - Sele��o de Sintomas</h3>
                                    <p>Abaixo, s�o apresentados o conjunto sintomas dispon�veis para a sele��o. Al�m disso, � necess�rio que seja escolhido ao menos 1 sintoma para que o sistema gere algum resultado relevante.</p>
                                </div>
                                <div class="card">
                                    <h4 class="card-header">Sintomas</h4>
                                    <div class="card-body"> 
                                        
                                        	<c:forEach items="${sintomas}" var="sintoma">
                                        		<label class="custom-control custom-checkbox">
                                                	<input type="checkbox" name="sintomas" value="${sintoma.iri}" class="custom-control-input"><span class="custom-control-label">${sintoma.label}</span>
                                            	</label>
                                        	</c:forEach>
                                    </div>
                                </div>
                                <div class="card">
                                    <h4 class="card-header">Tempo percebido dos sintomas</h4>
                                    <div class="card-body"> 
                                        <label for="tempo" class="col-form-label">Informe o tempo em meses</label>
                                        <input id="tempo" name="tempo" min="0" max="1080" type="number" class="form-control">
                                    </div>
                                </div>
                            	<div class="card">
                                    <h4 class="card-header">Situa��es</h4>
                                    <div class="card-body">                                         
                                        	<c:forEach items="${situacoes}" var="situacao">
                                        		<label class="custom-control custom-checkbox">
                                                	<input type="checkbox" name="situacoes" value="${situacao.iri}" class="custom-control-input"><span class="custom-control-label">${situacao.label}</span>
                                            	</label>
                                        	</c:forEach>                                        
                                    </div>
                                </div>
                                <div class="card">
                                    <div class="card-body"> 
										<input type="submit" value="Realizar An�lise" class="btn btn-success btn-block"/>
                                    </div>
                                </div>
                            </div>
                           </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ============================================================== -->
    <!-- end main wrapper -->
    <!-- ============================================================== -->
    <!-- Optional JavaScript -->
    <script src="assets/vendor/jquery/jquery-3.3.1.min.js"></script>
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.js"></script>
    <script src="assets/vendor/slimscroll/jquery.slimscroll.js"></script>
    <script src="assets/libs/js/main-js.js"></script>
    <script src="assets/vendor/inputmask/js/jquery.inputmask.bundle.js"></script>
    
</body>

</html>