/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessao;

import entidade.GrlMunicipio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ornela F. Boaventura
 */
@Stateless
public class GrlMunicipioFacade extends AbstractFacade<GrlMunicipio>
{

    @PersistenceContext(unitName = "SIG_HDP_Master-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager ()
    {
        return em;
    }

    public GrlMunicipioFacade ()
    {
        super(GrlMunicipio.class);
    }

    public List<GrlMunicipio> pesquisaPorProvincia (Integer idProvincia)
    {
        TypedQuery<GrlMunicipio> t = this.em.createQuery("SELECT m FROM GrlMunicipio m WHERE m.fkIdProvincia.pkIdProvincia = :idProvincia", GrlMunicipio.class).setParameter("idProvincia", idProvincia);

        List<GrlMunicipio> resultado = t.getResultList();

        return resultado;
    }
}
