es_un_pais(argentina).
es_un_pais(chile).
es_un_pais(brasil).
es_un_pais(peru).
es_un_pais(bolivia).
es_un_pais(uruguay).
limita_con(argentina,chile).
limita_con(uruguay,argentina).
limita_con(argentina,brasil).
limita_con(argentina,bolivia).
son_limitrofes(X,Y):-limita_con(X,Y);limita_con(Y,X).
son_translimitrofes(X,Y):-son_limitrofes(X,Z),son_limitrofes(Z,Y),not(son_limitrofes(X,Y)),not(X==Y).
pais_superficie(argentina,2780000).
pais_superficie(chile,756096).
pais_superficie(brasil,8516000).
pais_superficie(peru,1285000).
pais_superficie(bolivia,1099000).
pais_superficie(uruguay,176215).

producto_cartesiano(X,Y):-pais_superficie(_,X),pais_superficie(_,Y).
seleccion_menores(X,Y):-producto_cartesiano(X,Y),(X<Y).
proyeccion(X):-seleccion_menores(X,_).
maximo(X,Y):-pais_superficie(X,Y),not(proyeccion(Y)).
