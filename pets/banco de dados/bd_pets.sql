-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 27/11/2023 às 04:15
-- Versão do servidor: 10.4.28-MariaDB
-- Versão do PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE DATABASE `bd_pets`;
USE `bd_pets`;
--
-- Banco de dados: `bd_pets`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `registros`
--

CREATE TABLE `registros` (
  `num_registro` int(11) NOT NULL,
  `nome_pet` varchar(30) NOT NULL,
  `especie` varchar(30) NOT NULL,
  `raca` varchar(50) NOT NULL,
  `cor_pred` varchar(50) NOT NULL,
  `nascimento` date NOT NULL,
  `sexo` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `registros`
--

INSERT INTO `registros` (`num_registro`, `nome_pet`, `especie`, `raca`, `cor_pred`, `nascimento`, `sexo`) VALUES
(1, 'Luyyyy', 'Gato', 'Ragdoll', 'Cinza', '1111-11-11', 'Masculino'),
(2, 'Mia', 'Passaro', 'Calopsita', 'Amarelo', '2023-06-17', 'Feminino'),
(3, 'Ruby', 'Cadela', 'Raca indefinida', 'Caramelo', '2017-12-21', 'Feminino'),
(6, 'Billy', 'Cao', 'Raca indefinida', 'cinza', '1111-11-11', 'Feminino');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `registros`
--
ALTER TABLE `registros`
  ADD PRIMARY KEY (`num_registro`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `registros`
--
ALTER TABLE `registros`
  MODIFY `num_registro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
