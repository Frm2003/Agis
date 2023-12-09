use master
go
drop database agis

create database agis
go
use agis

select * from aluno
select * from matricula
select * from turma

--PROCEDURES
--gera dados
create procedure geraRa(@ra as varchar(9) output)
as
	declare @anoAtual as int = year(getdate())
	declare @semestreAtual as varchar(1)

	set @ra = '' + @anoAtual

	if (MONTH(getdate()) > 6) begin
		set @semestreAtual = '2'
	end else begin
		set @semestreAtual = '1'
	end

	set @ra = @ra + @semestreAtual

	declare @cont as int = 0

	while (@cont < 4) begin
		set @ra = @ra + cast(cast(rand() * 10 as int) as varchar(1))
		set @cont = @cont + 1
	end
go
create procedure calculaDataLimite(@saida as date output)
as
	set @saida = dateadd(year, 5, getDate())

--validacao
create procedure validarIdade(@dataNasc as date, @saida as bit output)
as
	declare @idade as int = datediff(year, @dataNasc, getdate())
	if (@idade >= 16) begin
		set @saida = 1
	end else begin
		set @saida = 0
	end
go
create procedure validarCPF(@cpf varchar(11), @valido bit output)
as	
	declare @Nr_Documento_Aux VARCHAR(11)
    set @Nr_Documento_Aux = LTRIM(RTRIM(@cpf))

	if (@Nr_Documento_Aux not in ('00000000000', '11111111111', '22222222222', '33333333333', '44444444444', '55555555555', '66666666666', '77777777777', '88888888888', '99999999999', '12345678909')) begin
		declare @saida varchar(100)
		declare @restDiv int = 0

		exec calculoCpf @cpf, 10, @restDiv output
		exec validaDigitoCpf @cpf, @restDiv, 10, @saida output

		if (@saida = 'valida') begin
			set @restDiv = 0
	
			exec calculoCpf @cpf, 11, @restDiv output
			exec validaDigitoCpf @cpf, @restDiv, 11, @saida output

			if (@saida = 'valida') begin
				set @valido = 1 
			end else begin
				set @valido = 0
			end
		end else begin
			set @valido = 0
		end
	end else begin 
		set @valido = 0
	end
go
create procedure calculoCpf(@cpf varchar(11), @cont as int, @result int output)
as
	declare @soma int = 0
	declare @val varchar(1)
	declare @pos int = 0

	while (@cont >= 2) begin
		set @pos = @pos + 1
		set @val = substring(@cpf, @pos, 1)
		set @soma = @soma + (cast(@val as int) * @cont)
		set @cont = @cont - 1
	end

	set @result = @soma % 11 
go
create procedure validaDigitoCpf(@cpf as varchar(11), @restDiv as int, @pos as int, @saida varchar(100) output)
as
	if (@restDiv >= 2) begin
		if (SUBSTRING(@cpf, @pos, 1) like cast(11 - @restDiv as varchar(1))) begin
			set @saida = 'valida'
		end else begin
			set @saida = 'invalida'
		end
	end else begin
		if (SUBSTRING(@cpf, @pos, 1) = '0') begin
			set @saida = 'valida'
		end else begin
			set @saida = 'invalida'
		end
	end 

--TRIGGERS
create trigger matriculaSemestreUm
on aluno
after insert
as
begin
    insert into matricula(ano, semestre, n1, n2, n3, situacao, raAluno, codTurma)
	select 
		cast(year(getdate()) as int) as ano,
        case when MONTH(getdate()) > 6 then '2' else '1' end as semestre,
		0,
		0,
		0,
		'cursando',
		i.ra,
		t.cod
	from inserted i
		inner join disciplina d on i.codCurso = d.codCurso
		inner join turma t on t.codDisciplina = d.cod
	where d.semestre = 1
end

enable trigger matriculaSemestreUm on aluno
disable trigger matriculaSemestreUm on aluno
