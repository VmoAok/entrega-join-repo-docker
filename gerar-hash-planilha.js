/utilizando javascript para gerar hash mascarando numero de documento, conforme política de dados. ferramenta: apps scripts google

function gerarHashMD5(valor) {
  var hash = Utilities.computeDigest(Utilities.DigestAlgorithm.MD5, valor);
  return hash.map(function(byte) {
    return ('0' + (byte & 0xFF).toString(16)).slice(-2);
  }).join('');
}
