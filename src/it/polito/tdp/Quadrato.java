package it.polito.tdp;

import java.util.ArrayList;
import java.util.List;

public class Quadrato {

	/**
	 * metodo per creare un quadrato magico (chiama la ricorsione partendo dal livello 0)
	 * @param N è la misura del lato n della matrice n*n
	 */
	public void creaQuadratoMagico(int N) {
		List<Integer> parziale = new ArrayList<>() ;
		
		ricerca(parziale, 0, N) ;
	}
	
	/**
	 * metodo ricorsivo : 
	 * @param parziale è la lista di soluzioni parziali
	 * @param livello indica l'iterazione nella ricorsione
	 * @param N è la misura del lato n della matrice n*n
	 */
	private void ricerca(List<Integer> parziale, int livello, int N) {
		
		if( livello == N*N ) {
			if(verifica(parziale, N))
				System.out.println(parziale);
		} else {
			if(livello%N==0 && livello!=0) {
				if(!verificaRiga(parziale, N, livello/N-1))
					return ;
			}

			// provo a mettere un valore nella nuova casella parziale[livello]
			for (int tentativo=1; tentativo<=N*N; tentativo++) {
				if( ! parziale.contains(tentativo)) {
					parziale.add(tentativo) ;
					ricerca(parziale, livello+1, N) ;
					parziale.remove(parziale.size()-1) ;
				}
			}
		}
	}
	/**
	 * metodo che compie i controlli per riga
	 * @param parziale è la lista di soluzioni parziali
	 * @param N è la misura del lato n della matrice n*n
	 * @param riga indica la riga su cui fare i controlli
	 * @return true se il valore nella casella del quadrato è compatibile
	 */
	private boolean verificaRiga(List<Integer> parziale, int N, int riga) {
		int somma = N * (N*N+1)/2 ;
		int s = 0 ;
		for(int col=0; col<N; col++) {
			s += parziale.get(riga*N+col) ;
		}
		return s==somma ;
	}
	
	/**
	 * metodo che compie i controlli di somma righe, colonne e diagonali
	 * @param parziale è la lista di soluzioni parziali
	 * @param N è la misura del lato n della matrice n*n
	 * @return true se il valore nella casella del quadrato è compatibile
	 */
	private boolean verifica(List<Integer> parziale, int N) {
		int somma = N * (N*N+1)/2 ;
		
		// somma delle righe
		for (int riga=0; riga<N; riga++) {
			int s = 0 ;
			for(int col=0; col<N; col++) {
				s += parziale.get(riga*N+col) ;
			}
			
			if ( s!=somma )
				return false ;
		}
		
		// somma delle colonne
		for (int col=0; col<N; col++) {
			int s = 0 ;
			for(int riga=0; riga<N; riga++) {
				s += parziale.get(riga*N+col) ;
			}
			
			if ( s!=somma )
				return false ;
		}
		
		// diagonale principale (s1) e secondaria (s2)
		int s1 = 0 ;
		int s2 = 0 ;
		for (int riga=0; riga<N; riga++) {
			s1 += parziale.get(riga*N+riga) ;
			s2 += parziale.get(riga*N+(N-1-riga)) ;
		}
		if(s1!=somma || s2!=somma)
			return false ;
		
		return true ;
	}
	
}